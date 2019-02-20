import java.util.ArrayList;

public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));;
            if(successful) {
                System.out.println("The bid for lot number " +
                                lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    
       
   public Lot getLot(int lotNumber)
   {
        if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
            // The number seems to be reasonable.
            Lot selectedLot = lots.get(lotNumber - 1);
            // Include a confidence check to be sure we have the
            // right lot.
            if(selectedLot.getNumber() != lotNumber) {
                System.out.println("Error: Lot number " +
                                   selectedLot.getNumber() +
                                   " was returned instead of " +
                                   lotNumber);
                // Don't return an invalid lot.
                selectedLot = null;
            }
            return selectedLot;
        }
        else {
            System.out.println("The Lot you have chosen: " + lotNumber +
                               " is not available.");
            return null;
        }
    }


  
    
    /**
 * Returns a list of unsold lots
 */
    public ArrayList<Lot> getUnsold()
     {
     ArrayList<Lot> unsoldLots = new ArrayList<Lot>();
        for(Lot lot : lots) {
            Bid highestBid = lot.getHighestBid();
            if(highestBid == null) {
                unsoldLots.add(lot);
            }
        }
        return unsoldLots;
     }

    
    public void close()
     {
         for(Lot lot : lots) {
       System.out.println(lot.getNumber() + ": " +
       lot.getDescription());
         // Include any details of a highest bid.
         Bid highestBid = lot.getHighestBid();
           if(highestBid != null) {
               System.out.println(" Highest bidder: " +
                    highestBid.getBidder().getName());
               System.out.println(" Bid: " +
                    highestBid.getValue());
        }
           else {
               System.out.println(" Not sold");
       }
      }
    }
    
    public Lot removeLot(int number)
     {
      Lot lot = getLot(number);
        if(lot != null) {
         lots.remove(lot);
     }
    return lot;
  }

 }

