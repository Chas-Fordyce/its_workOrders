import java.io.*;
import java.util.*;

public class Invoice
{
  // Fields
  int invoiceNumber;
  String date;
  String[] descriptionOfServices;
  double[] hours;
  String[] suggestionsForFutureServices;

  // Constructors
  public Invoice () throws FileNotFoundException
  {
    Scanner input = new Scanner(System.in);
    this.invoiceNumber = orderNumberGetter();
    this.date = dateGetter();
    this.descriptionOfServices = descriptionOfServicesGetter();
    this.hours = hoursGetter();
    this.suggestionsForFutureServices = suggestionsForFutureServicesGetter();

    invoiceCreator();
  }

  // File creator editors
  private void invoiceCreator () throws FileNotFoundException
  {
    // Create the file
    String str = "./Orders/invoice" + this.invoiceNumber + ".txt";
    File invoice = new File(str);

    // Get the template and make a writer to the new file
    Scanner fileIn = new Scanner(new File("./Orders/invoice_template.txt"));
    PrintWriter fileOut = new PrintWriter(invoice);

    // Write to the new file
    invoiceWriter(fileIn, fileOut);
    fileOut.close();
  }

  private void invoiceWriter (Scanner fileIn, PrintWriter fileOut) throws FileNotFoundException
  {
    // Write the invoice number and date in the first two lines
    fileOut.println(fileIn.nextLine() + " " + this.invoiceNumber);
    fileOut.println(fileIn.nextLine() + " " + this.date);

    // Writes unchanged items
    for (int i = 0; i < 9; i++)
    {
      fileOut.println(fileIn.nextLine());
    }

    // Writes the descriptions
    double count = 0;
    for (int i = 0; i < this.descriptionOfServices.length; i++)
    {
      String str = " - " + this.descriptionOfServices[i];
      for (int j = 0; j < (46 - (this.descriptionOfServices[i].length() + 3)); j++)
      {
          str += " ";
      }
      str += this.hours[i];

      fileOut.println(str);
      count += this.hours[i];
    }
    /*fileOut.println(*/fileIn.nextLine();//);
    fileOut.println();
    fileOut.println(fileIn.nextLine());
    // Total hours
    fileOut.println(fileIn.nextLine() + " " + count);
    fileOut.println(fileIn.nextLine());
    // Price per hour
    fileOut.println(fileIn.nextLine());
    // Grand total
    fileOut.println(fileIn.nextLine() + (count * 20));

    // Writes suggestions
    fileOut.println(fileIn.nextLine());
    fileOut.println(fileIn.nextLine());
    fileOut.println(fileIn.nextLine());
    for (int i = 0; i < this.suggestionsForFutureServices.length; i++)
    {
      fileOut.println(" - " + this.suggestionsForFutureServices[i]);
    }
    fileIn.nextLine();

    // Writes the rest
    while (fileIn.hasNext())
    {
      fileOut.println(fileIn.nextLine());
    }
  }

  // Field setters
  private int orderNumberGetter () throws FileNotFoundException
  {
    File file = new File("ordernumber.txt");
    // Reads the file and gets the number
    Scanner fileIn = new Scanner(file);
    int num = fileIn.nextInt();

    // Rewrites the file
    PrintWriter fileOut = new PrintWriter(file);
    fileOut.println((num + 1));
    fileOut.close();

    return num;
  }


  private String dateGetter ()
  {
    Scanner input = new Scanner(System.in);
    System.out.print("What's the date? ");
    return input.next();
  }

  private String[] descriptionOfServicesGetter ()
  {
    Scanner input = new Scanner(System.in);
    ArrayList<String> descriptions_temp = new ArrayList<String>();

    // Collect all the descriptions
    boolean keepGoing = true;
    while (keepGoing)
    {
      System.out.print("Service: ");
      String temp = input.nextLine();
      if (temp.equals("done"))
      {
        keepGoing = false;
        //break;
      }
      else
      {
        descriptions_temp.add(temp);
      }
    }

    // Add them to the array which is returned
    String[] descriptions = new String[descriptions_temp.size()];

    for (int i = 0; i < descriptions_temp.size(); i++)
    {
      descriptions[i] = descriptions_temp.get(i);
    }

    return descriptions;
  }

  private double[] hoursGetter ()
  {
    Scanner input = new Scanner(System.in);
    double[] hours_temp = new double[descriptionOfServices.length];
    for (int i = 0; i < descriptionOfServices.length; i++)
    {
      System.out.print(descriptionOfServices[i] + " : hours (double)? ");
      hours_temp[i] = input.nextDouble();
    }
    return hours_temp;
  }

  private String[] suggestionsForFutureServicesGetter ()
  {
    Scanner input = new Scanner(System.in);
    ArrayList<String> futureServices_temp = new ArrayList<String>();

    // Collect all the future services
    boolean keepGoing = true;
    while (keepGoing)
    {
      System.out.print("Future service: ");
      String temp = input.nextLine();
      if (temp.equals("done"))
      {
        keepGoing = false;
        //break;
      }
      else
      {
        futureServices_temp.add(temp);
      }
    }

    // Add them to the array which is returned
    String[] futureServices = new String[futureServices_temp.size()];

    for (int i = 0; i < futureServices_temp.size(); i++)
    {
      futureServices[i] = futureServices_temp.get(i);
    }

    return futureServices;
  }
}
