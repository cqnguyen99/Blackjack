//Duy Nguyen
//dcnguyen16@ole.augie.edu
//BlackJack.java
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class BlackJack extends JFrame implements ActionListener
{
	private DeckOfCards deck;
	private Vector<String> player;
	private Vector<String> dealer;
	private JLabel[] lblPCard = new JLabel[8];
	private JLabel[] lblDCard = new JLabel[8];
	private JButton btnDeal = new JButton("Deal");
	private JButton btnPlayer = new JButton("Player");
	private JButton btnDealer = new JButton("Dealer");
	private JButton btnNew = new JButton("New");
	private JButton btnAuthor = new JButton("Author");
	private JPanel pnlPlayerDealerInfo = new JPanel(new GridLayout(2, 8));
	private JPanel pnlButton = new JPanel(new FlowLayout());
	public BlackJack()
	{
		deck = new DeckOfCards();
		deck.shuffle();
		dealer = new Vector<String>();
		dealer.add(deck.deal());
		dealer.add(deck.deal());
		player = new Vector<String>();
		player.add(deck.deal());
		player.add(deck.deal());		
		addControls();
		registerListeners();
	}
	private void addControls()
	{
		add(pnlPlayerDealerInfo, BorderLayout.CENTER);
		add(pnlButton, BorderLayout.SOUTH);	
		for (int i = 0; i < 8; ++i)
			lblPCard[i] = new JLabel("Player", JLabel.CENTER);
		for (int i = 0; i < 8; ++i)
			lblDCard[i] = new JLabel("Dealer", JLabel.CENTER);
		pnlPlayerDealerAddControls();
		pnlButtonAddControls();
	}
	private void pnlPlayerDealerAddControls()
	{
		for (int i = 0; i < 8; ++i)
			pnlPlayerDealerInfo.add(lblPCard[i]);
		for (int i = 0; i < 8; ++i)
			pnlPlayerDealerInfo.add(lblDCard[i]);
	}
	private void pnlButtonAddControls()
	{
		pnlButton.add(btnDeal);
		pnlButton.add(btnPlayer);
		pnlButton.add(btnDealer);
		pnlButton.add(btnNew);
		pnlButton.add(btnAuthor);
		btnPlayer.setEnabled(false);
		btnDealer.setEnabled(false);
		btnNew.setEnabled(false);
	}
	public void registerListeners()
	{
		btnDeal.addActionListener(this);
		btnPlayer.addActionListener(this);
		btnDealer.addActionListener(this);
		btnNew.addActionListener(this);
		btnAuthor.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnDeal)
		{
			displayPlayer();
			displayDealer(true);
			btnPlayer.setEnabled(true);
			btnDeal.setEnabled(false);
		}
		if (e.getSource() == btnPlayer)
		{
			String choice = JOptionPane.showInputDialog(null, 
				"You have "+total(player)+". Hit or stay H/S",null);
			while (choice.equals("H") || choice.equals("h"))
			{
				player.add(deck.deal());
				displayPlayer();
				if (total(player) > 21) 
				{
					JOptionPane.showMessageDialog(null, 
						"You busted. Bye bye.");
					setEnabledNew();
					return;
				}
				choice = JOptionPane.showInputDialog(null, 
				"You have "+total(player)+". Hit or stay H/S",null);
			}
			btnPlayer.setEnabled(false);
			btnDealer.setEnabled(true);
		}
		if (e.getSource() == btnDealer)
		{
			while (total(dealer) < 17)
			{
				dealer.add(deck.deal());
			}
			displayDealer(false);
			whoWin(player, dealer);
			btnDealer.setEnabled(false);
			btnNew.setEnabled(true);
		}
		if (e.getSource() == btnNew)
		{
			for (int i = 0; i < 8; ++i)
			{
				lblPCard[i].setText("Player");
				lblPCard[i].setIcon(null);
			}
			for (int i = 0; i < 8; ++i)
			{
				lblDCard[i].setText("Dealer");
				lblDCard[i].setIcon(null);
			}
			newGame();
		}
		if (e.getSource() == btnAuthor)
			btnAuthor.setText("Chi");
	}
	private void displayPlayer()
	{
		Iterator<String> iter = player.iterator();
		while (iter.hasNext())
		{
			for (int i = 0; i < player.size(); ++i)
			{
				lblPCard[i].setIcon(new ImageIcon(
					"cardImages/"+iter.next()+".gif"));
				lblPCard[i].setText("");
			}
		}
	}
	private void displayDealer(boolean first)
	{
		Iterator<String> iter = dealer.iterator();
		if (first != true) 
			while (iter.hasNext())
			{
				for (int i = 0; i < dealer.size(); ++i)
				{
					lblDCard[i].setIcon(new ImageIcon(
						"cardImages/"+iter.next()+".gif"));
					lblDCard[i].setText("");
				}
			}
		else
		{
			lblDCard[0].setIcon(new ImageIcon(
				"cardImages/"+iter.next()+".gif"));
			lblDCard[0].setText("");
			lblDCard[1].setIcon(new ImageIcon(
				"cardImages/card.gif"));
			lblDCard[1].setText("");
		}
	}
	private int total(Vector<String> v)
	{
		int sum = 0;
		Iterator<String> iter = v.iterator();
		while (iter.hasNext())
		{
			sum += findRank(iter.next());
		}
		return sum;
	}
	public static int findRank(String s)
	{
		int result = 0;
		String sub = s.substring(0,3);
		switch(sub)
		{
			case "Ace": result = 11; break;
			case "Two": result = 2; break;
			case "Thr": result = 3; break;
			case "Fou": result = 4; break;
			case "Fiv": result = 5; break;
			case "Six": result = 6; break;
			case "Sev": result = 7; break;
			case "Eig": result = 8; break;
			case "Nin": result = 9; break;
			case "Ten": result = 10; break;
			case "Jac": result = 10; break;
			case "Que": result = 10; break;
			case "Kin": result = 10; break;
		}
		return result;
	}
	private void whoWin(Vector<String> player, Vector<String> dealer)
	{
		if (total(player) > total(dealer) || total(dealer) > 21)
			JOptionPane.showMessageDialog(null, "You won!");
		else if (total(player) < total(dealer))
			JOptionPane.showMessageDialog(null, "You lost.");
		else JOptionPane.showMessageDialog(null, "It's a tie.");
	}
	private void newGame()
	{
		deck = new DeckOfCards();
		deck.shuffle();
		dealer = new Vector<String>();
		dealer.add(deck.deal());
		dealer.add(deck.deal());
		player = new Vector<String>();
		player.add(deck.deal());
		player.add(deck.deal());
		btnDeal.setEnabled(true);
		btnNew.setEnabled(false);
	}
	private void setEnabledNew()
	{
		btnDeal.setEnabled(false);
		btnPlayer.setEnabled(false);
		btnDealer.setEnabled(false);
		btnNew.setEnabled(true); 
	}
	public static void main(String[] args)
   	{
		BlackJack f = new BlackJack();
		f.setTitle("Black Jack");
		f.setSize(650, 350);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);		
	}	
}