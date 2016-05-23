import java.io.*;
import java.util.*;

public class Invoice
{
  // Fields
  int invoiceNumber;
  String date;
  String[] descriptionOfServices;
  int[] hours;
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
    String str = "invoice" + this.invoiceNumber + ".txt";
    File invoice = new File(str);

    // Get the template and make a writer to the new file
    Scanner fileIn = new Scanner(new File("Orders\\invoice_template.txt"));
    PrintWriter fileOut = new PrintWriter(invoice);

    // Write to the new file
    invoiceWriter(fileIn, fileOut);
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
    int count = 0;
    for (int i = 0; i < this.descriptionOfServices.length; i++)
    {
      int num = 46 - this.descriptionOfServices[i].length();
      String space = " " * num;
      fileOut.println(" - " + this.descriptionOfServices[i] + space + this.hours[i]);
      count += this.hours[i];
    }
    fileOut.println(fileIn.nextLine());
    fileOut.println(fileIn.nextLine() + count);
    fileOut.println(fileIn.nextLine());
    fileOut.println(fileIn.nextLine());
    fileOut.println(fileIn.nextLine() + (count * 20));

    // Writes suggestions
    fileOut.println(fileIn.nextLine());
    fileOut.println(fileIn.nextLine());
    fileOut.println(fileIn.nextLine());
    for (int i = 0; i < this.suggestionsForFutureServices.length; i++)
    {
      fileOut.println(" - " + this.suggestionsForFutureServices[i]);
    }

    // Writes the rest
    while (fileIn.hasNext())
    {
      fileOut.println(fileIn.nextLine());
    }
  }

  // Field setters
  private int orderNumberGetter () throws FileNotFoundException
  {
    // Reads the file and gets the number
    Scanner input = new Scanner("ordernumber.txt");
    int num = input.nextInt();

    // Rewrites the file
    PrintWriter output = new PrintWriter(file);
		output.println((num + 1));
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
      System.out.println("Service: ");
      String temp = input.next();
      if (temp == "done")
      {
        keepGoing = false;
        break;
      }
      else
      {
        descriptions_temp.add(temp);
      }
    }

    // Add them to the array which is returned
    String[] descriptions = String[descriptions_temp.size()];

    for (int i = 0; i < descriptions_temp.size(); i++)
    {
      descriptions[i] = descriptions_temp.get(i);
    }

    return descriptions;
  }

  private int[] hoursGetter ()
  {
    Scanner input = new Scanner(System.in);
    int[] hours_temp = int[descriptionOfServices.length];
    for (int i = 0; i < descriptionOfServices.length; i++)
    {
      System.out.print(descriptionOfServices[i] + " ");
      hours_temp[i] = input.nextInt();
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
      System.out.println("Future service: ");
      String temp = input.next();
      if (temp == "done")
      {
        keepGoing = false;
        break;
      }
      else
      {
        futureServices_temp.add(temp);
      }
    }

    // Add them to the array which is returned
    String[] futureServices = String[futureServices_temp.size()];

    for (int i = 0; i < futureServices_temp.size(); i++)
    {
      futureServices[i] = futureServices_temp.get(i);
    }

    return futureServices;
  }
}
