
package com.company;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class GameBoardGUI extends JFrame {

	// Pekar ut res-mappen.
	public final String RESOURCE_PATH = "res/";
	
	// Ship.typeOfShip för träff.
	public final int DEAD_SHIP = -1;
	
	// Ship.typeOfShip för miss.
	public final int MISS = 8;
	
	// Ship.typeOfShip för skepp med längd 2 - 5.
	public final int SHIP_MIN = 2;
	public final int SHIP_MAX = 5;
	
	// När skepp är sänkt är rutorna runt skeppet dött utrymme. (Inget skepp får finnas där).
	public final int DEAD_SPACE = 6;

	// GameBoard för spelare.
	private GameBoard playerBoard;
	// GameBoard för fienden.
	private GameBoard enemyBoard;

	// Bredden på spelpanerna.
	private int width;
	// Höjden på spelplanerna.
	private int height;
	// Storleken på varje ruta på spelplanen.
	private int size;

	private String playerName;

	// Bakgrundsbilden för varje spelplan.
	private Image bgImage;

	// Panel som innehåller knappar för att kontrollera programmet.
	private JPanel control;
	// Panel som innehåller spelplanerna.
	private JPanel gameBoards;
	// Spelplanen för spelare.
	private JPanelWithBackground gameBoardPlayer;
	// Spelplanen för fienden.
	private JPanelWithBackground gameBoardEnemy;
	// Grid med labels för spelarens spelplan.
	private JLabel[][] playerGrid;
	// Grid med labels för fiendens spelplan.
	private JLabel[][] enemyGrid;
	// Label som innehåller namnet för spelaren.
	private JLabel playerLabel;
	// Label som innehåller namnet för fienden.
	private JLabel enemyLabel;
	// Button för att avsluta programmet.
	private JButton exit;
	

	 // title Titeln på fönstret.
	 // size Storlek (i pixlar) för varje ruta i griden för spelplanerna.

	public GameBoardGUI(String title, int size) {
		super(title);
		
		// Spara storleken för varje 
		this.size = size;
		
		// Sökvägen till bakgrundsbilden för spelplanerna.
		String imagePath = RESOURCE_PATH.concat("background.png");
		
		// Läs in bakgrundsbilden från sökvägen. 
		bgImage = Toolkit.getDefaultToolkit().createImage(imagePath);
		
	}

	// Sätter spelarens gameboard.
	public void setPlayerBoard(GameBoard board) {
		this.playerBoard = board;
		// Spara bredd och höjd av spelplanen.
		this.width = playerBoard.getColumns();
		this.height = playerBoard.getRows();
	}

	// Sätter fiendens gameboard.
	public void setEnemyBoard(GameBoard board) {
		this.enemyBoard = board;
	}

	// Sätter spelarens namn
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	// Initiera GUI:t.
	public final void init() {
		// Sätter layouten för denna JFrame till border layout.
		// Möjliga positioner: CENTER, NORTH, EAST, SOUTH, WEST.
		setLayout(new BorderLayout());
		
		// Panel som innehåller båda spelplanerna.
		gameBoards = new JPanel(new GridLayout(0, 1));
		
		// Label för spelarens namn.
		playerLabel = new JLabel(playerName);
		// Centrera texten horizontellt och verticalt.
		playerLabel.setHorizontalAlignment(JLabel.CENTER);
		playerLabel.setVerticalAlignment(JLabel.CENTER);
		
		// Label för fiendens namn.
		enemyLabel = new JLabel("Opponent");
		enemyLabel.setHorizontalAlignment(JLabel.CENTER);
		enemyLabel.setVerticalAlignment(JLabel.CENTER);
		
		// Panel för spelarens spelplan.
		JPanel playerPanel = new JPanel(new BorderLayout());
		// Skapa panel med bakgrund för spelaren
		gameBoardPlayer = new JPanelWithBackground(new GridLayout(width + 1, height + 1));
		// Sätt en border som är svart och fem pixlar bred.
		gameBoardPlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		// Panel för fiendens spelplan.
		JPanel enemyPanel = new JPanel(new BorderLayout());
		// Skapa panel med bakgrund för fienden.
		gameBoardEnemy = new JPanelWithBackground(new GridLayout(width + 1, height + 1));
		// Sätt en border som är svart och fem pixlar bred.
		gameBoardEnemy.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		// Skapa en panel för knappar.
		control = new JPanel(new FlowLayout());
		
		// Lägg till spelplanen och label för spelaren.
		playerPanel.add(gameBoardPlayer, BorderLayout.CENTER);
		playerPanel.add(playerLabel, BorderLayout.NORTH);
		
		// Lägg till spelplanen och label för fienden.
		enemyPanel.add(gameBoardEnemy, BorderLayout.CENTER);
		enemyPanel.add(enemyLabel, BorderLayout.SOUTH);
		
		// Lägg till spelaren och fiendens paneler till panelen spelplanen.
		gameBoards.add(playerPanel);
		gameBoards.add(enemyPanel);
		
		// Skapa knappen exit och sätt att den ska anropa exitGUI(0) när den klickas.
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitGUI(0);
			}
		});
		// Lägg till kanppen exit i panelen controll.
		control.add(exit);
		
		// Skapa grid som innehåller label som visar spelplanen för respektive spelare.
		playerGrid = new JLabel[width + 1][height + 1];
		enemyGrid = new JLabel[width + 1][height + 1];
		// Initiera respektive grid.
		for(int y = 0; y < height + 1; y++) {
			for(int x = 0; x < width + 1; x++) {
				initGameBoard(gameBoardPlayer, playerGrid, x, y);
				initGameBoard(gameBoardEnemy, enemyGrid, x, y);
			}
		}
		
		// Skapa labels på sidan (bokstäver) och nedtill (siffror).
		initLabels();
		
		// Lägg till spelplaner och controll i fönstret.
		add(gameBoards, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);
		
		// Centrera fönstret.
		setLocationRelativeTo(null);
		// Sätt att programmet stängs av när man stänger fönstret (ligger annars kvar i bakgrunden).
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Uppdatera GUI:t med spelplanernas innehåll.
		updateGameBoards(playerBoard, enemyBoard);
		
		// Paketera GUI:t (Anpassar storleken på fönstret till layouten).
		pack();

		// Sätt bakgrundsbilden för spelplanerna.
		gameBoardPlayer.setImage(bgImage);
		gameBoardEnemy.setImage(bgImage);
		
		// Visa fönstret.
		setVisible(true);
	}


	 // Initierar labeln på sidan och nedtill (bokstäver och siffror).

	private final void initLabels() {
		// Skapa bokstäverna till vänster.
		for(int y = 0; y < height; y++) {
			// Texten (A-J).
			String text = Character.toString(65 + y);
			initLabel(0, y, text);
		}
		
		// Skapa siffrorna längst ned.
		for(int x = 1; x < width + 1; x++) {
			// Texten (0-9).
			String text = Integer.toString(x - 1);
			initLabel(x, height, text);
		}
	}

	// Initiera etikett för bokstav/siffra.
	private void initLabel(int x, int y, String text) {

		// Etikett för spelaren.
		JLabel playerLabel = playerGrid[y][x];

		// Etikett för fienden.
		JLabel enemyLabel = enemyGrid[y][x];

			// Sätt texten för spelaren.
			playerLabel.setText(text);

			// Sätt texten för fienden.
			enemyLabel.setText(text);
		}



	 // Initierar den specificerade koordinaten för den specificerade spelplanen.
	 // spelplan Den panel som labels skall läggas till i.
	 // Griden med label där labels finns.
	 // x X-koordinat som labels finns på.
	 // y Y-koordinat som labels finns på.

	private void initGameBoard(JPanel spelplan, JLabel[][] grid, int x, int y) {
		// Skapa labeln.
		grid[y][x] = new JLabel();
		// Centrera innehållet horizontellt och vertikalt.
		grid[y][x].setHorizontalAlignment(JLabel.CENTER);
		grid[y][x].setVerticalAlignment(JLabel.CENTER);
		// Sätt storleken på labeln.
		grid[y][x].setPreferredSize(new Dimension(size, size));
		// Skapa en kant som är svart och 1 pixel bred.
		grid[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		// Lägg till gridlabel i panelen.
		spelplan.add(grid[y][x]);
	}


	 // Uppdaterar den grafiska spelplanen för spelaren och fienden.
	 // player Den logiska spelplanen för spelaren.
	// enemy Den logiska spelplanen för fienden.

	public void updateGameBoards(GameBoard player, GameBoard enemy) {
		// Hämta griden för varje spelplan (spelaren och fienden).
		Ship[][] playerShips = playerBoard.getPlayerBoard();
		Ship[][] enemyShips = enemyBoard.getPlayerBoard();
		
		// Loopar över spelplanen.
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				// Ropa på hjälpfunktion.
				updateGrid(playerGrid, playerShips, x, y);
				updateGrid(enemyGrid, enemyShips, x, y);
			}
		}
	}


	 // Uppdaterar etiketten på den specificerade koordinaten för den specificerade spelplanen.
	 // grid Den grafiska representationen av spelplanen.
	 // ships Den logiska representationan av spelplanen.
	 // x X-koordinaten som skall uppdateras.
	 // y Y-koordinaten som skall uppdateras.

	private void updateGrid(JLabel[][] grid, Ship[][] ships, int x, int y) {
		// Kontrollera om det finns något att visa.
		if(ships[y][x] == null) {
			// Ta bort bakgrund.
			grid[y][x + 1].setBackground(null);
			// Sätt labeln till genomskinlig så bakgrundsbilden på panelen syns.
			grid[y][x + 1].setOpaque(false);
		} else {
			// Hämta koodinatens typ.
			int type = ships[y][x].getTypeOfShip();
			// Kontrollera vilken typ som finns på koordinaten.
			if(type == DEAD_SHIP) {
				// Sätt röd bakgrundsfärg.
				grid[y][x + 1].setBackground(Color.RED);
				// Ta bort genomskinlighet för att visa bakgrundsfärgen.
				grid[y][x + 1].setOpaque(true);
			} else if(type == MISS) {
				// Sätt gul bakgrundsfärg.
				grid[y][x + 1].setBackground(Color.YELLOW);
				// Ta bort genomskinlighet för att visa bakgrundsfärgen.
				grid[y][x + 1].setOpaque(true);
			} else if(type >= SHIP_MIN && type <= SHIP_MAX) {
				// Sätt grön bakgrundsfärg (där spelarens skepp är)
				grid[y][x + 1].setBackground(Color.GREEN);
				// Ta bort genomskinlighet för att visa bakgrundsfärgen.
				grid[y][x + 1].setOpaque(true);
			} else if(type == DEAD_SPACE) {
				// Sätt grå bakgrundsfärg(Där skepp ej kan existera).
				grid[y][x + 1].setBackground(Color.GRAY);
				grid[y][x + 1].setOpaque(true);
			} else {
				// Ta bort bakgrund.
				grid[y][x + 1].setBackground(null);
				// Sätt etiketten till genomskinlig så bakgrundsbilden på panelen syns.
				grid[y][x + 1].setOpaque(false);
			}
		}
	}


	 // Visar en popupruta där spelare väljs.
	 // returnerar numret på vald spelare.

	public Integer selectPlayer() {
		// Titeln på popupfönstret.
		String title = "Player selection";
		// Meddelandet som visas i popupfönstret.
		String message = "Select the player you want to be:";
		
		// Val för spelare 1.
		Integer player1 = 1;
		// Val för spelare 2.
		Integer player2 = 2;
		
		// Array som innehåller de olika valen.
		Object[] selectionValues = new Integer[] {
				player1, player2
		};

		// Bild som används (måste existera för showinputdialog).
		ImageIcon imageIcon = null;
		
		// Visa popupfönster och spara det valda vädet.
		Object selected = JOptionPane.showInputDialog(this, message, title, JOptionPane.PLAIN_MESSAGE, imageIcon, selectionValues, player1);
		
		// Om popupfönstret stängdes eller om inget val gjordes.
		if(selected == null)
			// Returnera null.
			return null;
		
		// Konvertera det valda objektet till rätt class(Om du väljer spelare 1 eller spelare 2).
		return (Integer) selected;
	}


	    // Visa en popup som talarom att vi förlorat.

	public void showGameOver() {
		// Titeln på popupfönstret.
		String title = "Game Over";
		// Meddelandet som visas i popupfönstret.
		String message = "You have lost!";
		
		// Fil för ikon.
		File file = new File(RESOURCE_PATH, "gameover.png");
		
		ImageIcon imageIcon;
		// Kontrollera om filen finns.
		if(file.exists()) {
			// Filen existerar, använd ikonen.
			imageIcon = scaleImageIcon(file, size, size);
		} else {
			// Filen existerar inte, använd inte någon ikon.
			imageIcon = null;
		}
		
		// Visa popupfönstret(Gameover fönster).
		displayMessage(title, message, imageIcon);
	}


	 // Visa en popup som talar om att vi vunnit.

	public void showVictory() {
		// Titeln på popupfönstret.
		String title = "Success!";
		// Meddelandet som visas i popupfönstret.
		String message = "You have won!";
		
		// Fil för ikon.
		File file = new File(RESOURCE_PATH, "victory.png");
		
		ImageIcon imageIcon;
		// Kontrollera om filen finns.
		if(file.exists()) {
			// Filen existerar, använd ikonen.
			imageIcon = scaleImageIcon(file, size, size);
		} else {
			// Filen existerar inte, använd inte någon ikon.
			imageIcon = null;
		}

		// Visa popupfönstret.
		displayMessage(title, message, imageIcon);
	}

	 // Visar ett popupfönster med titel, meddelande (, eventuell ikon) och en ok-knapp.
	 // title Titeln på popupfönstret.
	 // message Meddelande som visas i popupfönstret.
	 // imageIcon Ikon som skall visas (om filen finns).

	private void displayMessage(String title, String message, ImageIcon imageIcon) {
		JOptionPane.showConfirmDialog(this, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon);
	}


	 // Stäng ned GUI och avsluta programmet.
	 // exitCode Exit code stänger ned programmet(Sluta köra koden helt).

	public void exitGUI(int exitCode) {
		// Dölj fönstret.
		setVisible(false);
		// Kassera fönstret.
		dispose();
		// Avsluta programmet och skicka med exit-koden.
		System.exit(exitCode);
	}


	 // Läser in bilden från fil och skalar till vald höjd och bredd.
	 // file Bildfilen som skall användas.
	 // width Bredden som bilden ska skalas till.
	 // height Höjden som bilden ska skalas till.
	 // returnerar bilden eller null om bilden inte finns.

	public static ImageIcon scaleImageIcon(File file, int width, int height) {
		Image image = scaleImage(file, width, height);
		ImageIcon imageIcon = new ImageIcon(image);
		return imageIcon;
	}


	 // Läser in bilden från fil och skalar till vald höjd och bredd.
	 // file Bildfilen som skall användas.
	 // width Bredden som bilden ska skalas till.
	 // height Höjden som bilden ska skalas till.
	 // returnerar bilden eller null om bilden inte finns.

	public static Image scaleImage(File file, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
		return scaleImage(imageIcon.getImage(), width, height);
	}


	 // Skalar bilden till vald storlek.
	 // image Bilden som ska skalas.
	 // width Bredden som bilden ska skalas till.
	 // height Höjden som bilden ska skalas till.
	 // returnerar bilden eller null om bilden inte finns.

	public static Image scaleImage(Image image, int width, int height) {
		return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

}

