import java.io.*;
import java.util.*;

public class Invoice
{
  // Fields
  int invoiceNumber;
  String date;
  String[] descriptionOfServices;
  String[] suggestionsForFutureServices;

  // Constructors
  public Invoice () throws FileNotFoundException
  {
    Scanner input = new Scanner(System.in);
    this.invoiceNumber = orderNumberGetter();
    this.date = dateGetter();
    this.descriptionOfServices = descriptionOfServicesGetter();
    this.suggestionsForFutureServices = suggestionsForFutureServicesGetter();
  }

  // File creator
  private void invoiceCreator () throws FileNotFoundException
  {
    
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

  private int orderTemplateGetter () throws FileNotFoundException
  {
    Scanner input = new Scanner("Orders\\workOrder_template.txt");
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
