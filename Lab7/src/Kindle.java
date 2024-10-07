public class Kindle {
    private int pageNumber;
    private int totalNumberOfPages;

    public Kindle(int numberOfPages) {
        totalNumberOfPages = numberOfPages;
        pageNumber = 1;
    } // End of Constructor Kindle

    public void turnPages(int numberOfPages) {
        if ((pageNumber + numberOfPages) > totalNumberOfPages) {
            System.out.print("Turning " + numberOfPages + " pages would take you past the last page.\nYou are now on             :");
            numberOfPages = (totalNumberOfPages - pageNumber);
        } // Displays Error Message and Displays Final Page Number as totalNumberOfPages

        pageNumber = pageNumber + numberOfPages; // Adds number of pages turned to pageNumber
    } // End of Method turnPages

    public void turnPages() {
        turnPages(1);
    } // End of Method turnPages

    public String toString() {
        return "Page " + pageNumber + " of " + totalNumberOfPages;
    } // End of Method toString
} // End of Class Kindle
//=================================================================================================
