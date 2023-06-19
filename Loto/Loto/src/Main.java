import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Integer numOfGeneratedTickets = 0;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        while (console.hasNextLine()) {
            String unos=console.nextLine().toLowerCase().trim();
            if(unos.equals("quick pick")){
                try {
                    storeNumbers(generateFiveRandomNumbers());
                } catch (IOException e) {
                    System.out.println("Problem sa pisanjem u fajl");
                }
            }
            else if(unos.equals("start draw") && numOfGeneratedTickets>=3){
                try {
                    startGameOfLoto();
                } catch (IOException e) {
                    System.out.println("Problem sa sistemom igre");
                }
            }
            else if(!unos.equals("start draw")) System.out.println("Nepoznata komanda");
            else System.out.println("Prije pocetka igre morate imate 3 listica");
        }

    }

    public static void startGameOfLoto() throws IOException {
        int prizeThird=0;
        int prizeSecond=0;
        int prizeFirst=0;
        System.out.println("Izvuceni brojevi su:");
        HashSet<String> gameNumbers=generateFiveRandomNumbers();
        for (int shots = 0; numOfGeneratedTickets >0 ; numOfGeneratedTickets--) {
            try(BufferedReader reader=new BufferedReader(new FileReader("Ticket"+numOfGeneratedTickets+".txt"))){
                String[] rez=reader.readLine().split(",");
                for (String s : rez) {
                    if (gameNumbers.contains(s)) shots++;
                }
                switch (shots) {
                    case 3 -> prizeThird++;
                    case 4 -> prizeSecond++;
                    case 5 -> prizeFirst++;
                }
                if(new File("Ticket"+numOfGeneratedTickets+".txt").delete())
                    System.out.println("Ticket broj "+numOfGeneratedTickets+" provjeren");
                else System.out.println("Problem sa brisanjem tiketa");
            }catch (IOException e){
                System.out.println("test");
            }
        }
        System.out.println("Igra je zavrsena\nBroj dobitaka prva vrste je:"+prizeFirst+"\nBroj dobitaka druge vrste je:" +
                prizeSecond+"\nBroj dobitaka trece vrste(JACKPOT) je:"+prizeThird);
    }

    public static void storeNumbers(HashSet<String> numbers){
        numOfGeneratedTickets++;
        try (FileWriter write = new FileWriter("Ticket" + numOfGeneratedTickets + ".txt")) {
            numbers.forEach(item -> {
                try {
                    write.write(item + ",");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static HashSet<String> generateFiveRandomNumbers() throws IOException {
        Random random = new Random();
        HashSet<String> numbers = new HashSet<>();
        while (numbers.size() < 5) {
            int generatedNumber = random.nextInt(1, 40);
            numbers.add(Integer.toString(generatedNumber));
        }
        numbers.forEach(System.out::println);
        return numbers;
    }
}