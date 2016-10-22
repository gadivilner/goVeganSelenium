package goVegan;

import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import utils.SeleniumUtils;

public class PublishVideosToGroups
{

	public void saveCredentials(String email, String password)
	{
		FileWriter fw = null;
		try
		{
			fw = new FileWriter("Credentials.txt");
			fw.write(email + " " + password);

		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
		finally
		{
			try
			{
				fw.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
