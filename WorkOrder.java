import java.io.*;
import java.util.*;

public class WorkOrder
{
	// Fields
	private int orderNumber;
	private String customerName;
	private String makeModel;

	private String dateBegin;
	private String timeBegin;

	private String dateComplete;
	private String timeComplete;

	// Constructors
	public WorkOrder ()
	{
		this.orderNumber = 0;
		this.customerName = null;
		this.makeModel = null;

		this.dateBegin = null;
		this.timeBegin = null;
		this.dateComplete = null;
		this.timeComplete = null;
	}

	public WorkOrder (int orderNumber, String customerName, String makeModel, String dateBegin, String timeBegin, String dateComplete, String timeComplete)
	{
		this.orderNumber = orderNumber;
		this.customerName = customerName;
		this.makeModel = makeModel;

		this.dateBegin = dateBegin;
		this.timeBegin = timeBegin;
		this.dateComplete = dateComplete;
		this.timeComplete = timeComplete;
	}

	// Setters
	public void setOrderNumber (int newOrderNumber)
	{
		this.orderNumber = newOrderNumber;
	}
	public void setCustomerName (String newCustomerName)
	{
		this.customerName = newCustomerName;
	}
	public void setMakeModel (String newMakeModel)
	{
		this.makeModel = newMakeModel;
	}

	// Getters
	public int getOrderNumber ()
	{
		return this.orderNumber;
	}
	public String getCustomerName ()
	{
		return this.customerName;
	}
	public String getMakeModel ()
	{
		return this.makeModel;
	}

	// Methods
	// Get newest work order number from file and updates the file to the next order number
	static int getNumberFromFile () throws FileNotFoundException()
	{
		File file = new File("ordernumber.txt");

		// Gets the current number
		Scanner input = new Scanner(file);
		int number = input.nextInt();

		// Sets the number one higher
		PrintStream output = new PrintStream(file);
		output.println((number + 1));
		output.close();

		return number
	}
}
