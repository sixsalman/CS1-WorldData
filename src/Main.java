import java.util.*; //whole of util package is imported
import java.io.*; //whole of io package is imported

/**
 *  Author: Salman
 *
 *  This program reads data about countries from a file, stores them in an ArrayList of objects and outputs in
 *  accordance to user's selection from a menu
 */
public class Main {

    private static ArrayList<Entry> entries; //reference created to hold objects which contain countries' information

    private static Scanner kbdNum = new Scanner(System.in); //Scanner kbdNum created to read user's numerical inputs
    private static Scanner kbdTxt = new Scanner(System.in); //Scanner kbdTxt created to read text inputs from user

    /**
     Main handles the driver code for the program.
     @param args is not used
     @throws IOException as it calls a method which can
     */
    public static void main(String[] args) throws IOException {

        getData();

        int opt;
        do {
            opt = getOpt();
            actOpt(opt);
        } while(opt != 9);

    }

    /**
     * This method gets data from file and stores it in objects in an ArrayList
     * @throws IOException when the file passed to scanner fails to exist
     */
    private static void getData() throws IOException {

        File file = new File("A5WorldData.csv"); //File referencing countries' data file created
        Scanner wData = new Scanner(file); //File passed to Scanner wData to read from file

        entries = new ArrayList<>(240); //ArrayList created to hold Entry objects

        String[] tokens = {"","","","","","0","0","0.0"};

        while (wData.hasNext()){
            entries.add(new Entry(tokens));
            wData.nextLine();
        }

        wData = new Scanner(file);

        wData.nextLine();

        while(wData.hasNext()){
            String entry = wData.nextLine();
            tokens = entry.split(",");
            entries.set(Integer.parseInt(tokens[1]), new Entry(tokens));
        }

        wData.close(); //file wData is closed

    }

    /**
     * Shows a menu and obtains a user's choice
     * @return user's chosen option
     */
    private static int getOpt() {

        int opt;
        boolean valid;
        do {
            System.out.println("CHOOSE OPTION (enter 1 to 9):\n" +
                    "1\t\tMedian population\n" +
                    "2\t\tMedian population - specify continent\n" +
                    "3\t\tMedian land size\n" +
                    "4\t\tMedian land size - specify continent\n" +
                    "5\t\tShow country - specify name\n" +
                    "6\t\tShow country - specify id\n" +
                    "7\t\tShow country - specify code\n" +
                    "8\t\tShow all countries – specify continent\n" +
                    "9\t\tQuit");
            opt = kbdNum.nextInt();
            if (!(Arrays.asList(1,2,3,4,5,6,7,8,9).contains(opt))){
                System.out.println("Invalid entry");
                valid = false;
            } else {
                valid = true;
            }
        } while(!valid);

        return opt;

    }

    /**
     * This method prints data as per user's choice previously, and for some choices, partially, obtained in this method
     * @param opt receives an integer corresponding to user's choice
     */
    private static void actOpt(int opt) {

        ArrayList<Entry> modEntries = new ArrayList<>(entries); //ArrayList modEntries created to hold a copy of entries

        int i;
        boolean found;
        double medIn;
        String cont;
        Comparator<Entry> comp;
        Iterator<Entry> itr;
        int first;
        int last;
        int mid;

        switch (opt){
            case 1:
                System.out.println("\nMedian population");
                comp = (a,b) -> a.getPop() - b.getPop();
                modEntries.sort(comp);
                medIn = (modEntries.size()) / 2.0;
                if ((medIn % 2) == 0) {
                    System.out.printf("\n%,d\n\n", (modEntries.get((int)medIn)).getPop());
                } else {
                    System.out.printf("\n%,d\n\n", (((modEntries.get((int)medIn)).getPop() +
                            (modEntries.get(((int)medIn) + 1)).getPop()) / 2));
                }
                break;
            case 2:
                System.out.println("\nMedian population - specify continent");
                cont = getOptCont();
                System.out.println("\n" + cont);
                cont = cont.toLowerCase();
                found = false;
                itr = modEntries.iterator();
                itr.next();
                while (itr.hasNext()){
                    if (!(((itr.next().getCont()).toLowerCase()).equals(cont))){
                        itr.remove();
                    } else {
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("\nContinent not found in record\n");
                    break;
                }
                comp = (a,b) -> a.getPop() - b.getPop();
                modEntries.sort(comp);
                medIn = (modEntries.size()) / 2.0;
                if ((medIn % 2) == 0) {
                    System.out.printf("\n%,d\n\n", (modEntries.get((int)medIn)).getPop());
                } else {
                    System.out.printf("\n%,d\n\n", (((modEntries.get((int)medIn)).getPop() +
                            (modEntries.get(((int)medIn) + 1)).getPop()) / 2));
                }
                break;
            case 3:
                System.out.println("\nMedian land size");
                comp = (a,b) -> a.getSize() - b.getSize();
                modEntries.sort(comp);
                medIn = (modEntries.size()) / 2.0;
                if ((medIn % 2) == 0) {
                    System.out.printf("\n%,d sq.miles\n\n", (modEntries.get((int)medIn)).getSize());
                } else {
                    System.out.printf("\n%,d sq.miles\n\n", (((modEntries.get((int)medIn)).getSize() +
                            (modEntries.get(((int)medIn) + 1)).getSize()) / 2));
                }
                break;
            case 4:
                System.out.println("\nMedian land size - specify continent");
                cont = getOptCont();
                System.out.println("\n" + cont);
                cont = cont.toLowerCase();
                found = false;
                itr = modEntries.iterator();
                itr.next();
                while (itr.hasNext()){
                    if (!(((itr.next().getCont()).toLowerCase()).equals(cont))){
                        itr.remove();
                    } else {
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("\nContinent not found in record\n");
                    break;
                }
                comp = (a,b) -> a.getSize() - b.getSize();
                modEntries.sort(comp);
                medIn = (modEntries.size()) / 2.0;
                if ((medIn % 2) == 0) {
                    System.out.printf("\n%,d sq.miles\n\n", (modEntries.get((int)medIn)).getSize());
                } else {
                    System.out.printf("\n%,d sq.miles\n\n", (((modEntries.get((int)medIn)).getSize() +
                            (modEntries.get(((int)medIn) + 1)).getSize()) / 2));
                }
                break;
            case 5:
                System.out.println("\nShow country - specify name");
                System.out.print("Specify name: ");
                String name = (kbdTxt.nextLine()).toLowerCase();
                comp = (a,b) -> a.getName().compareTo(b.getName());
                modEntries.sort(comp);
                found = false;
                first = 1;
                last = modEntries.size() - 1;
                i = 0;
                while (!found && first <= last){
                    mid = (first + last) / 2;
                    if ((((modEntries.get(mid)).getName()).toLowerCase()).equals(name)){
                        System.out.println("\n" + modEntries.get(mid) + "\n");
                        found = true;
                    } else if ((((modEntries.get(mid)).getName()).toLowerCase()).compareTo(name) > 0){
                        last = mid - 1;
                    } else {
                        first = mid + 1;
                    }
                    i++;
                }
                if (!found)
                    System.out.println("\nCode not found in record\n");
                System.out.println("SEARCH PATH >> " + i + " RECORD(S)\n");
                break;
            case 6:
                System.out.println("\nShow country - specify id");
                System.out.print("Specify ID: ");
                i = kbdNum.nextInt();
                if (i <= 0 || i >= entries.size()){
                    System.out.println("\nID not found in record\n");
                } else {
                    System.out.println("\n" + (entries.get(i)).toString(i) + "\n");
                }
                System.out.println("SEARCH PATH >> 1 RECORD\n");
                break;
            case 7:
                System.out.println("\nShow country - specify code");
                System.out.print("Specify code: ");
                String code = (kbdTxt.nextLine()).toLowerCase();
                comp = (a,b) -> a.getCode().compareTo(b.getCode());
                modEntries.sort(comp);
                found = false;
                first = 1;
                last = modEntries.size() - 1;
                i = 0;
                while (!found && first <= last){
                    mid = (first + last) / 2;
                    if ((((modEntries.get(mid)).getCode()).toLowerCase()).equals(code)){
                        System.out.println("\n" + modEntries.get(mid) + "\n");
                        found = true;
                    } else if ((((modEntries.get(mid)).getCode()).toLowerCase()).compareTo(code) > 0){
                        last = mid - 1;
                    } else {
                        first = mid + 1;
                    }
                    i++;
                }
                if (!found)
                    System.out.println("\nCode not found in record\n");
                System.out.println("SEARCH PATH >> " + i + " RECORD(S)\n");
                break;
            case 8:
                System.out.println("\nShow all countries – specify continent");
                cont = getOptCont();
                System.out.println("\n" + cont);
                cont = cont.toLowerCase();
                found = false;
                System.out.print("\n");
                for (i = 1; i < entries.size(); i++){
                    if ((((entries.get(i)).getCont()).toLowerCase()).equals(cont)){
                        System.out.println((entries.get(i)).toString(i));
                        found = true;
                    }
                }
                if (!found)
                    System.out.println("Continent not found in record");
                System.out.print("\n");
                break;
                default:
                    System.out.println("\nQuit");
                    kbdNum.close(); //Scanner kbdNum is closed
                    kbdTxt.close(); //Scanner kbdTxt is closed
                    System.out.println("\nProgram will now quit.\n");
        }

        System.out.println("********************\n");

    }

    /**
     * Shows a menu and obtains a user's continent choice
     * @return user's chosen continent option
     */
    private static String getOptCont() {
        int opt;
        boolean valid = false;
        do {
            System.out.println("\nEnter corresponding number:\n1\tAfrica\n2\tAntarctica\n3\tAsia\n4\tEurope\n5\t" +
                    "North America\n" + "6\tOceania\n7\tSouth America");

            opt = kbdNum.nextInt();
            if (!(Arrays.asList(1,2,3,4,5,6,7).contains(opt))) {
                System.out.println("Invalid entry");
            } else {
                valid = true;
            }
        } while (!valid);
        String cont = "";
        switch (opt){
            case 1:
                cont = "Africa";
                break;
            case 2:
                cont = "Antarctica";
                break;
            case 3:
                cont = "Asia";
                break;
            case 4:
                cont = "Europe";
                break;
            case 5:
                cont = "North America";
                break;
            case 6:
                cont = "Oceania";
                break;
            case 7:
                cont = "South America";
                break;
        }
        return cont;

    }

}
