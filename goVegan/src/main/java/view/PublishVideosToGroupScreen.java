package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import goVegan.MostViewedVideos;
import goVegan.PublishVideosToGroups;
import utils.Constants;
import utils.FacebookUtils;
import utils.SeleniumUtils;

public class PublishVideosToGroupScreen extends JFrame
{

	private static final long serialVersionUID = 1L;

	private JTextField emailTextField;
	private JPasswordField passwordTextField;

	private JTextArea linksTextArea;
	private JTextArea groupsTextArea;
	private JCheckBox checkBoxbRememberMe;
	private JButton buttonSend;

	private PublishVideosToGroups publishVideosToGroups;

	public static void main(String[] args)
	{
		new PublishVideosToGroupScreen();
	}

	public PublishVideosToGroupScreen()
	{
		super("Publish Videos To Groups by Gadi Vilner");
		publishVideosToGroups = new PublishVideosToGroups();
		initComponents();
		addComponentsEvents();
		addTestCredentials();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void addTestCredentials()
	{
		emailTextField.setText(Constants.EMAIL_GADI_VILNER);
		passwordTextField.setText(Constants.PASSWORD_GADI_VILNER);

		linksTextArea.setText("https://www.facebook.com/ChallengeTwentyTwo/videos/1838212809745613/\nhttps://www.facebook.com/ChallengeTwentyTwo/videos/1835724599994434/\nhttps://www.facebook.com/ChallengeTwentyTwo/videos/1828354670731427/\nhttps://www.facebook.com/ChallengeTwentyTwo/videos/1823677537865807/");
		groupsTextArea.setText(
				"https://www.facebook.com/groups/torontoveg/?ref=group_browse_new\nhttps://www.facebook.com/groups/bhutanvegetarian/?ref=group_browse_new\nhttps://www.facebook.com/groups/cvchelp/?ref=group_browse_new\nhttps://www.facebook.com/groups/221889257972996/?ref=group_browse_new\nhttps://www.facebook.com/groups/235179373502681/?ref=group_browse_new\nhttps://www.facebook.com/groups/352286458280582/?ref=group_browse_new\nhttps://www.facebook.com/groups/135713326579851/?ref=group_browse_new\nhttps://www.facebook.com/groups/ogrosvegans/?ref=group_browse_new\nhttps://www.facebook.com/groups/leipzig.vegan/?ref=group_browse_new\nhttps://www.facebook.com/groups/birminghamvegans/?ref=group_browse_new");
	}

	private void addComponentsEvents()
	{
		buttonSend.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String email = emailTextField.getText();
				String password = new String(passwordTextField.getPassword());
				if (checkBoxbRememberMe.isSelected())
				{
					publishVideosToGroups.saveCredentials(email, password);
				}

				String[] links = getLinksFromGui();
				String[] groups = getGroupsFromGui();
				SeleniumUtils.openBrowser();
				FacebookUtils.navigateToFacebook();
				FacebookUtils.login(email, password);
				postLinksInGroups(links, groups);
			}
		});
	}

	public void postLinksInGroups(String[] links, String[] groups)
	{
		for (String group : groups)
		{
			if (group.equals("https://www.facebook.com/groups/torontoveg/?ref=group_browse_new"))
			{
				continue;
			}
			SeleniumUtils.navigateToUrl(group);
			// boolean first = true;
			// try
			// {
			// for (String link : links)
			// {
			// try
			// {
			// Thread.sleep(5000);
			// SeleniumUtils.setText(SeleniumUtils.findElement(By.xpath("//*[@id=\"facebook\"]/body")),
			// link);
			// Thread.sleep(5000);
			// Actions actions = new Actions(SeleniumUtils.getDriver());
			// int until = first ? 14 : 13;
			// first = false;
			// for (int i = 1; i <= until; i++)
			// {
			// actions.sendKeys(Keys.TAB).perform();
			// }
			//
			// actions.sendKeys(Keys.ENTER).perform();
			// }
			// catch (Exception e)
			// {
			// // TODO: handle exception
			// }
			// }
			//
			// Thread.sleep(5000);
			// }
			// catch (Exception e)
			// {
			// // TODO: handle exception
			// }
		}
	}

	protected String[] getGroupsFromGui()
	{
		return getStrings(groupsTextArea);
	}

	protected String[] getLinksFromGui()
	{
		return getStrings(linksTextArea);
	}

	private String[] getStrings(JTextArea textArea)
	{
		return textArea.getText().split("\n");
	}

	private void initComponents()
	{
		emailTextField = new JTextField(20);
		passwordTextField = new JPasswordField(20);
		linksTextArea = new JTextArea();
		groupsTextArea = new JTextArea();
		checkBoxbRememberMe = new JCheckBox("Remember me");
		buttonSend = new JButton("Go!");

		JPanel mainPanel = new JPanel();
		JPanel credentialsPanel = new JPanel();
		JPanel emailPanel = new JPanel();
		JPanel passwordPanel = new JPanel();
		JPanel linksPanel = new JPanel();
		JPanel groupsPanel = new JPanel();

		mainPanel.setLayout(new GridLayout(1, 3));

		linksPanel.setLayout(new BorderLayout());
		linksPanel.add(new JLabel("links to send:"), BorderLayout.NORTH);
		linksPanel.add(linksTextArea, BorderLayout.CENTER);
		mainPanel.add(linksPanel);

		credentialsPanel.setLayout(new FlowLayout());

		emailPanel.setLayout(new FlowLayout());
		emailPanel.add(new JLabel("e-mail:", SwingConstants.LEFT));
		emailPanel.add(emailTextField);
		credentialsPanel.add(emailPanel);

		passwordPanel.setLayout(new FlowLayout());
		passwordPanel.add(new JLabel("password:", SwingConstants.LEFT));
		passwordPanel.add(passwordTextField);
		credentialsPanel.add(passwordPanel);
		credentialsPanel.add(checkBoxbRememberMe);
		credentialsPanel.add(buttonSend);

		mainPanel.add(credentialsPanel);

		groupsPanel.setLayout(new BorderLayout());
		groupsPanel.add(new JLabel("groups to send to:"), BorderLayout.NORTH);
		groupsPanel.add(groupsTextArea, BorderLayout.CENTER);
		mainPanel.add(groupsPanel);

		this.getContentPane().add(mainPanel);
		this.setSize(1100, 800);
	}
}
