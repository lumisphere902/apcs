
//Timothy Poon P1 2/27/2017
//1 hour
//Overall, this lab was rather fun. Initially, I had no idea what to do,
//but eventually decided on implementing our first semester "Creature" lab
//in this new environment. It was much more intuitive this time around,
//as I never really grew to like the "DrawingTool" way of drawing things.
//However, one thing that was easier to do in gpdraw was the squiggly line
//for the tail.

//Timothy Poon P1 3/1/2017
//40 minutes
//Overall, this lab continues to be quite interesting. One common project
//for beginners to creating GUIs is some sort of pet simulator, so I decided
//to take that concept and use it for my mock UI. It went along well with the
//pig I created for the first part of the lab, and it had a few easy inclusions
//into the UI.

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class P1_Poon_Timothy_Layout extends Application {
	private final double BODY_RADIUS = 200;
	private final double NOSE_SIZE = BODY_RADIUS / 5;
	private final double EYE_RADIUS = BODY_RADIUS / 10;
	private final double HEAD_RADIUS = BODY_RADIUS * 0.7;
	private final double EAR_RADIUS = BODY_RADIUS / 5;
	private final double INNER_EAR_RADIUS = EAR_RADIUS * 0.7;
	private final double NOSTRIL_RADIUS = NOSE_SIZE / 3;
	private final double TAIL_RADIUS = HEAD_RADIUS / 2;
	private Color PINK1 = Color.DEEPPINK;
	private Color PINK2 = Color.HOTPINK;
	private Color PINK3 = Color.PINK;
	private Color PINK4 = Color.LIGHTPINK;
	private Color PINK5 = Color.BISQUE;
	private Circle body;
	private Circle leftEar;
	private Circle rightEar;
	private Circle innerLeftEar;
	private Circle innerRightEar;
	private Circle head;
	private Circle leftEye;
	private Circle rightEye;
	private Circle leftNostril;
	private Circle rightNostril;
	private Ellipse nose;
	private Group picture;
	private Label confirmedName;
	private TextField name;
	private HBox header;
	private final String[] statTypes = { "Health", "Happiness", "Energy", "Food", "Water" };
	private final String[] actionTypes = { "Take to vet", "Play with", "Sleep", "Feed", "Give water", };
	private final String[] deathMessages = { "a sickness.", "depression.", "lack of sleep.", "malnutrition.",
			"thirst." };
	private final ArrayList<IdButton> buttons = new ArrayList<IdButton>();
	private final ArrayList<ProgressIndicator> PIs = new ArrayList<ProgressIndicator>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Pig");
		stage.setResizable(false);
		stage.sizeToScene();

		VBox root = new VBox();
		Scene scene = new Scene(root, BODY_RADIUS * 4, BODY_RADIUS * 4);
		stage.setScene(scene);

		header = new HBox();
		header.setPadding(new Insets(50));
		header.setAlignment(Pos.CENTER);
		Label title = new Label("Your pet pig named ");
		name = new TextField();
		confirmedName = new Label();
		name.setOnKeyReleased(new NameHandler());
		header.getChildren().addAll(title, name, confirmedName);

		HBox center = new HBox();
		center.setAlignment(Pos.CENTER);
		VBox stats = new VBox();
		stats.setPadding(new Insets(30, 50, 30, 30));
		;
		stats.setAlignment(Pos.CENTER);
		for (String stat : statTypes) {
			HBox temp = new HBox();
			temp.setAlignment(Pos.CENTER_RIGHT);
			Label tempLabel = new Label(stat);
			ProgressIndicator pi = new ProgressIndicator(Math.random());
			temp.getChildren().addAll(tempLabel, pi);
			stats.getChildren().add(temp);
			PIs.add(pi);
		}
		picture = createPicture(scene);
		center.getChildren().addAll(stats, picture);

		HBox bottom = new HBox();
		bottom.setAlignment(Pos.CENTER);
		bottom.setPadding(new Insets(50));
		int i = 0;
		for (String action : actionTypes) {
			Image img = new Image("file:" + action + ".png", 24, 24, false, false);
			ImageView imgView = new ImageView(img);
			IdButton temp = new IdButton(action, imgView, i);
			ButtonClickHandler handler = new ButtonClickHandler();
			temp.setOnMouseClicked(handler);
			bottom.getChildren().add(temp);
			buttons.add(temp);
			i++;
		}
		root.getChildren().addAll(header, center, bottom);

		stage.show();
	}

	private class ButtonClickHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			// System.out.println(event);
			int badTraits = 0;
			int id = ((IdButton) event.getSource()).getID();
			for (int i = 0; i < PIs.size(); i++) {
				double initialValue = PIs.get(i).getProgress();
				if (i == id) {
					PIs.get(i).setProgress(initialValue > .79 ? .99 : initialValue + .2);
				} else {
					if (initialValue <= 0.05) {
						Alert gameOver = new Alert(Alert.AlertType.INFORMATION, (confirmedName.getText().isEmpty()
								? "Your pig" : confirmedName.getText()) + " has died from " + deathMessages[i]);
						gameOver.showAndWait();
						System.exit(0);
					} else if (initialValue <= .45) {
						badTraits++;
					}
					PIs.get(i).setProgress(initialValue - .05);
				}
			}
			setColors(badTraits);
		}

	}

	private class NameHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			System.out.println(1);
			System.out.println("Given: " + event.getCode() + ", Enter: " + KeyCode.ENTER);
			if (event.getCode() != KeyCode.ENTER) {
				return;
			}
			System.out.println(2);
			if (name.getText().isEmpty()) {
				return;
			}
			System.out.println(3);
			confirmedName.setText(name.getText());
			header.getChildren().remove(1);
		}

	}

	private void setColors(int num) {
		if (num == 0) {
			PINK1 = Color.DEEPPINK;
			PINK2 = Color.HOTPINK;
			PINK3 = Color.PINK;
			PINK4 = Color.LIGHTPINK;
			PINK5 = Color.BISQUE;
		} else if (num < 3) {
			PINK1 = Color.DARKBLUE;
			PINK2 = Color.BLUE;
			PINK3 = Color.DEEPSKYBLUE;
			PINK4 = Color.LIGHTSKYBLUE;
			PINK5 = Color.LIGHTBLUE;
		} else {
			PINK1 = Color.DARKGREEN;
			PINK2 = Color.FORESTGREEN;
			PINK3 = Color.GREEN;
			PINK4 = Color.LAWNGREEN;
			PINK5 = Color.LIGHTGREEN;
		}
		updateColors();
	}

	private void updateColors() {
		body.setFill(PINK1);
		leftEar.setFill(PINK2);
		rightEar.setFill(PINK2);
		head.setFill(PINK3);
		nose.setFill(PINK4);
		innerLeftEar.setFill(PINK5);
		innerRightEar.setFill(PINK5);
	}

	private Group createPicture(Scene scene) {
		Group picture = new Group();
		body = new Circle(BODY_RADIUS);
		body.setCenterX(scene.getWidth() / 2);
		body.setCenterY(scene.getHeight() / 2);
		body.setFill(PINK1);
		body.setStroke(Color.BLACK);
		picture.getChildren().add(body);

		leftEar = new Circle(EAR_RADIUS);
		leftEar.setCenterX(body.getCenterX() - HEAD_RADIUS / 2);
		leftEar.setCenterY(body.getCenterY() - HEAD_RADIUS * Math.sqrt(3) / 2);
		leftEar.setFill(PINK2);
		leftEar.setStroke(Color.BLACK);
		picture.getChildren().add(leftEar);

		innerLeftEar = new Circle(INNER_EAR_RADIUS);
		innerLeftEar.setCenterX(leftEar.getCenterX());
		innerLeftEar.setCenterY(leftEar.getCenterY());
		innerLeftEar.setFill(PINK5);
		picture.getChildren().add(innerLeftEar);

		rightEar = new Circle(EAR_RADIUS);
		rightEar.setCenterX(body.getCenterX() + HEAD_RADIUS / 2);
		rightEar.setCenterY(body.getCenterY() - HEAD_RADIUS * Math.sqrt(3) / 2);
		rightEar.setFill(PINK2);
		rightEar.setStroke(Color.BLACK);
		picture.getChildren().add(rightEar);

		innerRightEar = new Circle(INNER_EAR_RADIUS);
		innerRightEar.setCenterX(rightEar.getCenterX());
		innerRightEar.setCenterY(rightEar.getCenterY());
		innerRightEar.setFill(PINK5);
		picture.getChildren().add(innerRightEar);

		head = new Circle(HEAD_RADIUS);
		head.setCenterX(body.getCenterX());
		head.setCenterY(body.getCenterY() + HEAD_RADIUS / 8);
		head.setFill(PINK3);
		head.setStroke(Color.BLACK);
		picture.getChildren().add(head);

		leftEye = new Circle(EYE_RADIUS);
		leftEye.setCenterX(body.getCenterX() - HEAD_RADIUS / 3);
		leftEye.setCenterY(body.getCenterY() - HEAD_RADIUS * Math.sqrt(3) / 4);
		leftEye.setFill(Color.BLACK);
		picture.getChildren().add(leftEye);

		rightEye = new Circle(EYE_RADIUS);
		rightEye.setCenterX(body.getCenterX() + HEAD_RADIUS / 3);
		rightEye.setCenterY(body.getCenterY() - HEAD_RADIUS * Math.sqrt(3) / 4);
		rightEye.setFill(Color.BLACK);
		picture.getChildren().add(rightEye);

		nose = new Ellipse();
		nose.setCenterX(body.getCenterX());
		nose.setCenterY(body.getCenterY() + NOSE_SIZE * 1.5);
		nose.setRadiusX(NOSE_SIZE * 2);
		nose.setRadiusY(NOSE_SIZE);
		nose.setFill(PINK4);
		nose.setStroke(Color.BLACK);
		picture.getChildren().add(nose);

		leftNostril = new Circle(NOSTRIL_RADIUS);
		leftNostril.setCenterX(nose.getCenterX() - NOSE_SIZE);
		leftNostril.setCenterY(nose.getCenterY());
		leftNostril.setFill(Color.BLACK);
		picture.getChildren().add(leftNostril);

		rightNostril = new Circle(NOSTRIL_RADIUS);
		rightNostril.setCenterX(nose.getCenterX() + NOSE_SIZE);
		rightNostril.setCenterY(nose.getCenterY());
		rightNostril.setFill(Color.BLACK);
		picture.getChildren().add(rightNostril);

		Arc tail1 = new Arc(body.getCenterX() + BODY_RADIUS / Math.sqrt(2) + TAIL_RADIUS,
				body.getCenterY() + BODY_RADIUS / Math.sqrt(2), TAIL_RADIUS, TAIL_RADIUS * .7, 180, 180);
		tail1.setFill(null);
		tail1.setStroke(Color.BLACK);
		tail1.setStrokeWidth(3);
		picture.getChildren().add(tail1);
		Arc tail2 = new Arc(body.getCenterX() + BODY_RADIUS / Math.sqrt(2) + TAIL_RADIUS * 1.5,
				body.getCenterY() + BODY_RADIUS / Math.sqrt(2), TAIL_RADIUS * .5, TAIL_RADIUS * .5, 0, 180);
		tail2.setFill(null);
		tail2.setStroke(Color.BLACK);
		tail2.setStrokeWidth(3);
		picture.getChildren().add(tail2);
		Arc tail3 = new Arc(body.getCenterX() + BODY_RADIUS / Math.sqrt(2) + TAIL_RADIUS * 2,
				body.getCenterY() + BODY_RADIUS / Math.sqrt(2), TAIL_RADIUS, TAIL_RADIUS, 180, 225);
		tail3.setFill(null);
		tail3.setStroke(Color.BLACK);
		tail3.setStrokeWidth(3);
		picture.getChildren().add(tail3);
		return picture;
	}

	private class IdButton extends Button {
		private int id;

		public IdButton(String text, Node n, int id) {
			super(text, n);
			this.id = id;
		}

		public IdButton(String text, int id) {
			super(text);
			this.id = id;
		}

		public IdButton(int id) {
			super();
			this.id = id;
		}

		public int getID() {
			return id;
		}
	}
}
