import java.util.*;
class Book
{
	int id; 
	String name;
	int copies;
	ArrayList<String> NAMES = new ArrayList<>();
	Book(int id,String name,int copies)
	{
		this.id = id;
		this.name= name;
		this.copies= copies;
	}
	Book(String name)
	{this.name=name;}
	public boolean equals(Object o)
	{
		Book b = (Book)o;
		if(this.name.equals(b.name))
			{
				b.copies++; 
				System.out.println(this.name+" Already Exists with Book Id:"+b.id);
				System.out.println(" Total Number of Copies:"+b.copies);
				return true;
			}
		else return false;
	}

}
class Member
{
	String name;
	int id;
	ArrayList<String>BOOKS = new ArrayList<>();
	Member(int id,String name)
	{this.id=id;this.name=name;}
	Member(String name)
	{
		this.name=name;
	}
	public String toString()
	{
		return id+" "+name;
	}
	public boolean equals(Object o)
	{
		Member m = (Member)o;
		if(this.name.equals(m.name))
			return true;
		else return false;
	}
}
public class library {
	public ArrayList<Member>mem = new ArrayList<>();
	public ArrayList<Book> book = new ArrayList<>();
	public Scanner scan= new Scanner(System.in);
	public static library lib= new library();
	public void addMember()
	{
		System.out.println("Enter New Member Name:");
		String name = scan.nextLine();
		 name = scan.nextLine();
		if(mem.contains(new Member(name)))
		System.out.println(name+" Already Present");
		else
			{
				int id = mem.size()+1;
				Member m1= new Member(id,name);
				mem.add(m1);
				System.out.println(name+" Added with Member Id:"+id);
			}
			System.out.println("Do You Want to Enter New Member:");
			System.out.println("Press Y/N");
			char ch = scan.next().charAt(0);
			if(ch=='Y'|| ch=='y')
				addMember();
			else menu();
	}
	public void showMembers()
	{
		System.out.println(mem);
	}
	public void addBook()
	{
		System.out.println("Enter New Book Name:");
		String name = scan.nextLine();
		name = scan.nextLine();
		if(book.contains(new Book(name)))
		{}
		else{
			System.out.println("Enter the number of Copies You want to Add:");
			int copies = scan.nextInt();
			int id = book.size()+1;
			System.out.println("New Book Added:"+name);
			System.out.println("  Book Id:"+id);
			System.out.println("Number of Copies:"+copies);
			System.out.println();
			book.add(new Book(id,name,copies));
		}
		System.out.println("Do You Want to Enter New Book:");
		System.out.println("Press Y/N");
		char ch = scan.next().charAt(0);
		if(ch=='Y'|| ch=='y')
			addBook();
		else menu();
	
	}
	public void borrowing()
	{
		System.out.println("Enter your Valid Member Id:");
		int memid = scan.nextInt();
		if(memid>mem.size())
		{
			System.out.println("Member doesn't Exist");
			borrowing();
		}
		else{
			System.out.println("Enter Book Id:");
			int bookid = scan.nextInt(),c=0;
			while(bookid>book.size()){
				System.out.println("Invalid Book Id..Please Enter a valid Book Id");
				bookid = scan.nextInt();
			}
			
				Iterator <Book>itr = book.iterator();
				Book b=null;
				while(itr.hasNext())
				{
					 b = itr.next();
					if(b.id==bookid && b.copies>=1)
					{	
						b.copies--;
						c=1;
						break;
					}
					
				}
				
				if(c==1)
					{
					
						Member m = mem.get(memid-1);
						m.BOOKS.add(b.name);
						b.NAMES.add(m.name);
						System.out.println("Book issued to :"+m.name);
						System.out.println("Name of Book:"+b.name);
					}
				else System.out.println("Book is not Available");
			
		
		System.out.println("Do You want to process any oter Borrowing?");
		System.out.println("Press Y/N");
		char ch = scan.next().charAt(0);
		if(ch=='Y'|| ch=='y')
			borrowing();
		else menu();
		}
	}
	
	public void returning()
	{
		System.out.println("Enter your Valid Member Id:");
		int memid = scan.nextInt();
		if(memid>mem.size())
		{
			System.out.println("Member doesn't Exist");
			returning();
		}
		else{
			System.out.println("Enter Book Id:");
			int bookid = scan.nextInt(),c=0;
			while(bookid>book.size()){
				System.out.println("Invalid Book Id..Please Enter a valid Book Id");
				bookid = scan.nextInt();
			}
			
				Iterator <Book>itr = book.iterator();
				Book b=null;
				while(itr.hasNext())
				{
					 b = itr.next();
					 Member m2 = mem.get(memid-1);
					if(b.id==bookid && m2.BOOKS.contains(b.name))
					{	
						b.copies++;
						c=1;
						break;
					}
				}
				if(c==1)
				{
				
					Member m = mem.get(memid-1);
					m.BOOKS.remove(b.name);
					b.NAMES.remove(m.name);
					System.out.println("*Book Returned*");
					System.out.println("Member: "+m.name);
					System.out.println("Book returned: "+b.name);
					System.out.println("After returning Number of Books "+b.name+" available in stock :"+b.copies);
				}
				else System.out.println("Book not Returned");
					
				
		
		System.out.println("Do You want to process any oter Returning?");
		System.out.println("Press Y/N");
		char ch = scan.next().charAt(0);
		if(ch=='Y'|| ch=='y')
			returning();
		else menu();
		}
	}
	public void memberStatus()
	{
		System.out.println("Enter your Valid Member Id:");
		int memid = scan.nextInt();
		if(memid>mem.size() || memid==0)
		{
			System.out.println("Member doesn't Exist");
			memberStatus();
		}
		else
		{
			Member m = mem.get(memid-1);
			System.out.println("Member ["+m.id+"] : "+m.name);
			System.out.print("Borrowed Books: ");
			if(m.BOOKS.size()==0)
				System.out.print("None");
			else System.out.print(m.BOOKS);
			System.out.println();
		
		System.out.println("Do You want to see any oter Member Status?");
		System.out.println("Press Y/N");
		char ch = scan.next().charAt(0);
		if(ch=='Y'|| ch=='y')
			memberStatus();
		else menu();
		}
	}
	public void bookStatus()
	{
		System.out.println("Please Enter Book Id:");
		int bookid =scan.nextInt();
		if(bookid>book.size() || bookid==0)
		{
			System.out.println("Please Enter a valid Book Id");
			menu();
		}
		else
		{
			Book b = book.get(bookid-1);
			System.out.println("Book ["+b.id+"] :"+b.name);
			System.out.println("Available Books:"+b.copies);
			System.out.println("List of Member Borrowing:"+b.NAMES);
		 
		System.out.println("Do You want to see any oter Book Status?");
		System.out.println("Press Y/N");
		char ch = scan.next().charAt(0);
		if(ch=='Y'|| ch=='y')
			bookStatus();
		else menu();
		}
	}
	public void menu()
	{
		System.out.println("Please Select an Option:");
		System.out.println("1) Add New Member");
		System.out.println("2) Add New Book");
		System.out.println("3) Process Borrowing");
		System.out.println("4) Process Returning");
		System.out.println("5) View Member Status");
		System.out.println("6) View Book Status");
		System.out.println("7) Quit");
		System.out.println("Enter your Option:");
		int opt = scan.nextInt();
		switch(opt)
		{
		case 1:
			lib.addMember();
			break;
		case 2: 
			lib.addBook();
			break;
		case 3:
			lib.borrowing();
			break;
		case 4:
			lib.returning();
			break;
		case 5:
			lib.memberStatus();
			break;
		case 6:
			lib.bookStatus();
			break;
		case 7:
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("......Lirary Management System.....");
		 lib.menu();
		
	}
}