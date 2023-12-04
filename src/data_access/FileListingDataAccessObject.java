package data_access;

import entity.*;
import use_case.book_info.BookInfoDataAccessInterface;
import use_case.create_listing.CreateListingDataAccessInterface;
import use_case.delete_listing.DeleteListingDataAccessInterface;
import use_case.listings.ListingsDataAccessInterface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class FileListingDataAccessObject implements CreateListingDataAccessInterface, BookInfoDataAccessInterface, DeleteListingDataAccessInterface, ListingsDataAccessInterface {
    /**
     * A Data Access Object that stores all listing information except images.
     * @param csvPath the String that represents a filepath for the file that stores Listings.
     * @param listingFactory a Factory for creating Listings.
     * @param bookPhoto: a photo of the book, default or uploaded by the seller.
     */
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Listing> listingInfo = new HashMap<>();
    private final Map <String, Listing> sellerToListing = new HashMap<>();
    private final Map <Listing, String> listingToISBN = new HashMap<>();
    private Map <String, Listing> imagePathToListing = new HashMap<>();
    private Map <String, String> isbnToTitle = new HashMap<>();
    private Map <String, CommonUser> usernameToSeller = new HashMap<>();
    private Map <String, File> imagePathToPhoto = new HashMap<>();
    private ListingFactory listingFactory;
    private BufferedImage storedImage;
    private File savedPhoto;
    private File imageDirectory;
    private File userDirectory;

    /** Constructing a Data Access Object with a csv for Strings corresponding to Listings' data.
     *
     * @param csvPath
     * @param listingFactory
     * @throws IOException
     */
    public FileListingDataAccessObject(String csvPath, ListingFactory listingFactory) throws IOException {
        this.listingFactory = listingFactory;
        csvFile = new File(csvPath);
        headers.put("isbn", 0);
        headers.put("seller", 1);
        headers.put("listing_price", 2);
        headers.put("condition", 3);
        headers.put("listingId", 4);
        headers.put("creation_time", 5);

        storedImage = new BufferedImage(5, 5, 1);

        File imageDirectory = new File("allImages");
        if (!imageDirectory.exists()){
            imageDirectory.mkdir();
        }

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("isbn,seller,listing_price,condition,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String isbn = String.valueOf(col[headers.get("isbn")]);
                    String sellerUsername = (col[headers.get("seller")]);
                    double listing_price = Double.parseDouble(col[headers.get("listing_price")]);
                    String condition = String.valueOf(col[headers.get("condition")]);
                    String listingId = String.valueOf(col[headers.get("listingId")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    Listing listing = listingFactory.create(isbnToTitle.get(isbn), isbn, sellerUsername,
                            listing_price, condition, savedPhoto, ldt);
                    listingInfo.put(listingId, listing);
                }
            }
        }
    }

    /**
     * Maps a listing ID to a listing object, then saves the listing information to a local system.
     * @param listing
     * @throws IOException
     */
    @Override
    public void save(Listing listing) throws IOException {
        listingInfo.put(listing.getListingId(), listing);

        // Use a default image if the image directory is null or the book photo is not set
        if (imageDirectory == null || listing.getBookPhoto() == null) {
            // Path to your default image inside your project
            String defaultImagePath = "default.png";
            storedImage = ImageIO.read(new File(defaultImagePath));
        } else {
            // If imageDirectory is not null and book photo is set, proceed with normal operations
            File userDirectory = new File(listing.getSeller());
            if (!userDirectory.exists()) {
                userDirectory.mkdir();
            }

            Path imageDirFullpath = Paths.get(imageDirectory.getAbsolutePath());
            Path userDirFullpath = Paths.get(userDirectory.getAbsolutePath());

            // Perform file operations as necessary
            // ...

            storedImage = ImageIO.read(listing.getBookPhoto());
        }

        this.save();
    }
    /**
     * Clears the listing data file.
     */
    public void clear() throws IOException {
        listingInfo.clear();
        this.save();
    }

    private void save() throws IOException {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            String defaultImagePath = "path/to/default.png";  // Update this path as necessary
            File defaultImageFile = new File(defaultImagePath);

            for (Listing listing : listingInfo.values()) {
                isbnToTitle.put(listing.getISBN(), listing.getTitle());
                sellerToListing.put(listing.getSeller(), listing);
                imagePathToListing.put(listing.getPathId(), listing);
                listingToISBN.put(listing, listing.getISBN());

                // Store the default image file instead of reading the image
                imagePathToPhoto.put(listing.getPathId(), defaultImageFile);

                String line = String.format("%s,%s,%s,%s,%s,%s",
                        listing.getISBN(), listing.getSeller(), listing.getPrice(),
                        listing.getCondition(), listing.getListingId(), listing.getCreationTime());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Return whether a listing exists with listingId identifier.
     * @param listingId the listingId to check.
     * @return whether a listing exists with listingId identifier
     */
    public boolean existsById(String listingId) {
        return listingInfo.containsKey(listingId);
    }
    /**
     * Deletes the listing with the given listing ID
     * @param listingId the listingId to delete
     * @return the listingId as a string.
     */
    @Override
    public String delete(String listingId){
        for(String id : listingInfo.keySet()){
            if(id.equals(listingId)){
                listingInfo.remove(listingId);
            }
        }
        try {
            this.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listingId;
    }

    /**
     * Returns the listings of the given username in a list
     * @param username
     * @return
     */
    public List<Listing> getUserListings(String username) {
        List<Listing> listings = new ArrayList<>();
        for (Listing listing : listingInfo.values()){
            if (listing.getSeller().equals(username)){
                listings.add(listing);
            }
        }

        return listings;
    }

    public List<Listing> getBookListings(String ISBN) {
        List<Listing> listings = new ArrayList<>();
        for (Listing listing : listingInfo.values()) {
            if (listing.getISBN().equals(ISBN)) {
                listings.add(listing);
            }
        }

        return listings;
    }

    @Override
    public String findCity(String username) {
        return null;
    }

    @Override
    public String findEmail(String username) {
        return null;
    }

    @Override
    public String findPhoneNumber(String username) {
        return null;
    }
}