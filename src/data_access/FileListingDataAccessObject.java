package data_access;

import entity.*;
import use_case.create_listing.CreateListingDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileListingDataAccessObject implements CreateListingDataAccessInterface {
    /**
     * A Data Access Object that stores all listing information except images.
     * @param csvPath the String that represents a filepath for the file that stores Listings.
     * @param listingFactory a Factory for creating Listings.
     * @param bookPhoto: a photo of the book, default or uploaded by the seller.
     */
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Listing> listingInfo = new HashMap<>();
    private File bookPhoto;
    private ListingFactory listingFactory;

    /** Constructing a Data Access Object for listing information except images.
     *
     * @param csvPath
     * @param listingFactory
     * @param bookPhoto
     * @throws IOException
     */
    public FileListingDataAccessObject(String csvPath, ListingFactory listingFactory, File bookPhoto,
                                       CommonUser seller, Book book) throws IOException {
        this.listingFactory = listingFactory;
        this.bookPhoto = bookPhoto;
        csvFile = new File(csvPath);
        headers.put("isbn", 0);
        headers.put("seller", 1);
        headers.put("listing_price", 2);
        headers.put("condition", 3);
        headers.put("listingId", 4);
        headers.put("creation_time", 5);

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
                    int isbn = Integer.parseInt(col[headers.get("isbn")]);
                    String sellerUsername = (col[headers.get("seller")]);
                    double listing_price = Double.parseDouble(col[headers.get("listing_price")]);
                    String condition = String.valueOf(col[headers.get("condition")]);
                    String listingId = String.valueOf(col[headers.get("listingId")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    Listing listing = listingFactory.create(book, seller, listing_price, condition, bookPhoto, ldt);
                    listingInfo.put(listingId, listing);
                }
            }
        }
    }

    /**
     * Maps a listing ID to a listing object, then saves the listing information to a local system.
     * @param listing
     */
    @Override
    public void save(Listing listing) {
        listingInfo.put(listing.getListingId(), listing);
        this.save();
    }
    /**
     * Clears the listing data file.
     */
    public void clear(){
        listingInfo.clear();
        this.save();
    }
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Listing listing : listingInfo.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%s",
                        listing.getBook().getISBN(), listing.getSeller(), listing.getPrice(), listing.getCondition(), listing.getListingId(), listing.getCreationTime());
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
}


