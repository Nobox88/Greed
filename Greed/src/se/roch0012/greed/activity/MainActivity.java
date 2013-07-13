package se.roch0012.greed.activity;

import java.util.ArrayList;
import java.util.Stack;

import se.roch0012.greed.R;
import se.roch0012.greed.handler.PointCalculator;
import se.roch0012.greed.model.Dices;
import se.roch0012.greed.model.States;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * This is not ready yet. Need a couple of hours more to finish this
 * 
 * @author roch0012
 * 
 */

public class MainActivity extends Activity {
	ImageButton[] dices;
	Dices diceValue;
	States states;
	int totalScore;
	int currentScore;
	int round;
	int nrOfThrows;

	PointCalculator calc = new PointCalculator();

	/**
	 * Sets what view is on the activity And also prepares listeners to the
	 * dices
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// This is for 6 dices
		diceValue = new Dices(6);
		states = new States(6);

		dices = new ImageButton[] { (ImageButton) findViewById(R.id.dice_1),
				(ImageButton) findViewById(R.id.dice_2),
				(ImageButton) findViewById(R.id.dice_3),
				(ImageButton) findViewById(R.id.dice_4),
				(ImageButton) findViewById(R.id.dice_5),
				(ImageButton) findViewById(R.id.dice_6) };

		// Gives every dice a listener
		for (ImageButton dice : dices) {
			dice.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					diceToggle(v);

				}
			});
		}

		// Listener for the throw button
		Button throwButton = (Button) findViewById(R.id.throw_button);
		throwButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				throwDices();

			}
		});

		Button saveButton = (Button) findViewById(R.id.save_button);
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				save();

			}
		});
		reset();
	}

	/**
	 * Inflates the menu in the activity
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * This handles what happens when an item is pressed in the menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.reset:
			reset();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * This handles what happens when a dice is clicked
	 * 
	 * @param v
	 */
	private void diceToggle(View v) {
		int id = Integer.parseInt((String) v.getTag());
		switch (states.getState(id)) {
		case States.OLD:
			break;
		case States.NOT_SELECTED:
			states.setState(id, States.SELECTED);
			break;
		case States.SELECTED:
			states.setState(id, States.NOT_SELECTED);
			break;
		}
		updateGui();

	}

	// TODO: Decides what happens when the throw button i pressed
	private void throwDices() {
		nrOfThrows++;
		ArrayList<ImageButton> willThrow = new ArrayList<ImageButton>();
		ArrayList<Integer> pointDices = new ArrayList<Integer>();

		for (ImageButton dice : dices) {
			int id = Integer.parseInt((String) dice.getTag());
			switch (states.getState(id)) {
			case States.OLD:
				pointDices.add(id);
				break;
			case States.SELECTED:
				states.setState(id, States.OLD);
				pointDices.add(id);
				break;
			case States.NOT_SELECTED:
				willThrow.add(dice);
				break;
			}
		}
		if (pointDices.size() > 0) currentScore = calc.calculate(pointDices);
		else currentScore = 0;
		updateGui();
	}

	// TODO: Will save the points depending on what is selected
	private void save() {

	}

	/**
	 * This returns the right drawable resource depending inb the state and the
	 * dice value
	 * 
	 * @param state
	 * @param i
	 *            The number of eyes on the dice
	 * @return
	 */
	public Drawable getDiceImg(int state, int i) {
		switch (state) {
		case States.OLD:
			switch (i) {
			case 1:
				return getResources().getDrawable(R.drawable.grey1);
			case 2:
				return getResources().getDrawable(R.drawable.grey2);
			case 3:
				return getResources().getDrawable(R.drawable.grey3);
			case 4:
				return getResources().getDrawable(R.drawable.grey4);
			case 5:
				return getResources().getDrawable(R.drawable.grey5);
			case 6:
				return getResources().getDrawable(R.drawable.grey6);
			}
		case States.SELECTED:

			switch (i) {
			case 1:
				return getResources().getDrawable(R.drawable.red1);
			case 2:
				return getResources().getDrawable(R.drawable.red2);
			case 3:
				return getResources().getDrawable(R.drawable.red3);
			case 4:
				return getResources().getDrawable(R.drawable.red5);
			case 5:
				return getResources().getDrawable(R.drawable.red5);
			case 6:
				return getResources().getDrawable(R.drawable.red6);

			}
		case States.NOT_SELECTED:
			switch (i) {
			case 1:
				return getResources().getDrawable(R.drawable.white1);
			case 2:
				return getResources().getDrawable(R.drawable.white2);
			case 3:
				return getResources().getDrawable(R.drawable.white3);
			case 4:
				return getResources().getDrawable(R.drawable.white5);
			case 5:
				return getResources().getDrawable(R.drawable.white5);
			case 6:
				return getResources().getDrawable(R.drawable.white6);

			}
		default:
			return null;
		}

	}

	/**
	 * Resets the game by randomizing the dices and reseting the number of
	 * rounds
	 */
	public void reset() {
		int rnd;
		for (ImageButton dice : dices) {
			int id = Integer.parseInt((String) dice.getTag());
			rnd = 1 + (int) (Math.random() * ((6 - 1) + 1));
			states.setState(id, States.NOT_SELECTED);
			diceValue.setDice(id, rnd);
		}
		round = 0;
		currentScore = 0;
		totalScore = 0;
		throwDices();
		updateGui();
		System.out.println("The dices: " + diceValue);
	}

	/**
	 * Updates the GUI
	 */
	public void updateGui() {
		for (ImageButton dice : dices) {
			int id = Integer.parseInt((String) dice.getTag());
			dice.setImageDrawable(getDiceImg(states.getState(id),
					diceValue.getDice(id)));
		}
		((TextView) findViewById(R.id.total_score)).setText("" + totalScore);
		((TextView) findViewById(R.id.current_score))
				.setText("" + currentScore);
	}
}
