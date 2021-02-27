import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Dijkstra dijkstra = new Dijkstra();
        Scanner input = new Scanner(System.in);
        ExecutePython executePython = new ExecutePython(".\\src\\map.py");
        Thread t = new Thread(executePython);
        t.start();
        while (true) {
            System.out.print(">");
            String command = input.nextLine();
            if (command.equals("exit"))
                break;
            FileWriter myWriter = new FileWriter("rout.txt");
            ArrayList<Node> rout = dijkstra.shortestRout(Double.parseDouble(command.split(" ")[0]), command.split(" ")[1], command.split(" ")[2]);
            System.out.println(rout.get(0).getCost() * 120);
            myWriter.write(rout.size()+"");
            myWriter.write("\n");
            for (int i = 0; i < rout.size(); i++) {
                System.out.print(rout.get(rout.size() - i - 1).getIdentification() + " ");
                myWriter.write(rout.get(rout.size() - i - 1).getLongitude() + " " + rout.get(rout.size() - i - 1).getLatitude());
                myWriter.write("\n");
            }
            myWriter.close();
            ExecutePython e = new ExecutePython(".\\src\\rout.py");
            Thread thread = new Thread(e);
            thread.start();
            System.out.println();
        }
    }
} 