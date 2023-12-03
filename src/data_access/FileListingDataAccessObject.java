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

public class FileListingDataAccessObject implements CreateListingDataAccessInterface, BookInfoDataAccessInterface {
    /**
     * A Data Access Object that stores all listing information except images.
     * @param csvPath the String that represents a filepath for the file that stores Listings.
     * @param listingFactory a Factory for creating Listings.
     * @param bookPhoto: a photo of the book, default or uploaded by the seller.
     */
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Listing> listingInfo = new HashMap<>();
    private final Map <CommonUser, Listing> sellerToListing = new HashMap<>();
    private final Map <Book, Listing> bookToListing = new HashMap<>();
    private Map <String, Listing> imagePathToListing = new HashMap<>();
    private Map <String, Book> isbnToBook = new HashMap<>();
    private Map <String, CommonUser> usernameToSeller = new HashMap<>();
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

        imageDirectory = new File("allImages");
        if (!imageDirectory.exists()){
            imageDirectory.mkdir();
        }

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("isbn,seller,listing_price,condition,listingId,creation_time");

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
                    isbnToBook.get(isbn).setEverInStock();
                    Listing listing = listingFactory.create(isbnToBook.get(isbn), usernameToSeller.get(sellerUsername),
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
        userDirectory = new File(listing.getSeller().getUsername());
        if(!userDirectory.exists()){
            userDirectory.mkdir();
            Path imageDirFullpath = Paths.get(imageDirectory.getAbsolutePath());
            Path userDirFullpath = Paths.get(userDirectory.getAbsolutePath());
            Files.move(userDirFullpath, imageDirFullpath);
        }
        File defaultImage = new File("default.png");
        storedImage = ImageIO.read((listing.getBookPhoto()));
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

            for (Listing listing : listingInfo.values()) {
                isbnToBook.put(listing.getBook().getISBN(), listing.getBook());
                sellerToListing.put(listing.getSeller(), listing);
                imagePathToListing.put(listing.getPathId(), listing);
                bookToListing.put(listing.getBook(), listing);
                usernameToSeller.put(listing.getSeller().getUsername(), listing.getSeller());
                String line = String.format("%s,%s,%s,%s,%s,%s",
                        listing.getBook().getISBN(), listing.getSeller(), listing.getPrice(), listing.getCondition(), listing.getListingId(), listing.getCreationTime());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            savedPhoto = new File("bookPhoto.png");
            ImageIO.write(storedImage, "png", savedPhoto);
            Files.move(Paths.get(savedPhoto.getAbsolutePath()), Paths.get(imageDirectory.getAbsolutePath()));
        } catch(IOException e){
            System.out.println("There was a problem saving the image.");
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
    public String delete(String listingId) throws IOException {
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
     * Return a list of Listings created by a seller.
     * @param username the seller's username.
     * @return a list of Listings create by a seller.
     */

    @Override
    public List<Listing> getUserListings(String username) {
        List<Listing> listings = new ArrayList<>();
        for (Listing listing : listingInfo.values()){
            if (listing.getSeller().getUsername().equals(username)){
                listings.add(listing);
            }
        }

        return listings;
    }

    public List<Listing> getBookListings(String ISBN) {
        List<Listing> listings = new ArrayList<>();
        for (Listing listing : listingInfo.values()) {
            if (listing.getBook().getISBN().equals(ISBN)) {
                listings.add(listing);
            }
        }

        return listings;
    }

    /**
     * Return a map of listingIds to Listings for testing.
     * @return a map of listingIds to listing objects
     */
    public Map<String, Listing> getListingInfo(){
        return listingInfo;
    }

}
