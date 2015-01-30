package th.ac.tu.siit.its333.lab2exercise1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // expr = the current string to be calculated
    StringBuffer expr;
    double mem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expr = new StringBuffer();
        updateExprDisplay();
    }

    public void updateExprDisplay() {
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(expr.toString());
    }

    public void updateAnsDisplay(String s) {
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(s);
    }
    public void recalculate() {
        //Calculate the expression and display the output
         //Split expr into numbers and operators
        //e.g. 123+45/3 --> ["123", "+", "45", "/", "3"]
        //reference: http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        String e = expr.toString();
        String[] tokens = e.split("((?<=\\+)|(?=\\+))|((?<=\\-)|(?=\\-))|((?<=\\*)|(?=\\*))|((?<=/)|(?=/))");
        if (!tokens[0].equals("")) {

            double result;
            result = Double.parseDouble(tokens[0]);
            for (int i = 1; i < tokens.length - 1; i++) {
                if (tokens[i].equals("+")) {
                    result += Double.parseDouble(tokens[i + 1]);
                } else if (tokens[i].equals("-")) {
                    result -= Double.parseDouble(tokens[i + 1]);
                } else if (tokens[i].equals("*")) {
                    result *= Double.parseDouble(tokens[i + 1]);
                } else if (tokens[i].equals("/")) {
                    result /= Double.parseDouble(tokens[i + 1]);
                }
            }
            updateAnsDisplay(Double.toString(result));

        }
        else
        {
            updateAnsDisplay(Double.toString(0));
        }
    }

    public void digitClicked(View v) {
        //d = the label of the digit button
        String d = ((TextView)v).getText().toString();
        //append the clicked digit to expr
        expr.append(d);
        //update tvExpr
        updateExprDisplay();
        //calculate the result if possible and update tvAns
        recalculate();
    }

    public void operatorClicked(View v) {
        //IF the last character in expr is not an operator and expr is not "",
        if (!expr.toString().isEmpty() && !isOperand(expr.charAt(expr.length()-1) )){
                String d = ((TextView)v).getText().toString();
            //append the clicked digit to expr
            expr.append(d);
            //update tvExpr
            updateExprDisplay();
        }
        //THEN append the clicked operator and updateExprDisplay,
        //ELSE do nothing

    }

    private boolean isOperand(char c){
        return c == '/' || c == '+' || c=='-' || c=='*';
    }

    public void mcClick (View v){
       mem = 0;
        Toast t = Toast.makeText(this.getApplicationContext(),
                "Memory cleared", Toast.LENGTH_SHORT);
        t.show();


    }

    public void mplusClick (View v){
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        String s = tvAns.getText().toString();
        mem += Double.parseDouble(s);

        Toast t = Toast.makeText(this.getApplicationContext(),
               mem + " has been added to memory", Toast.LENGTH_SHORT);
        t.show();


    }

    public void mrClick (View v){
        expr = new StringBuffer();
        expr.append(Double.toString(mem));
        updateExprDisplay();
        updateAnsDisplay(Double.toString(mem));


    }

    public void mminusClick (View v){
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        String s = tvAns.getText().toString();
        mem -= Double.parseDouble(s);

        Toast t = Toast.makeText(this.getApplicationContext(),
                mem + " has been added to memory", Toast.LENGTH_SHORT);
        t.show();


    }

    public void equalClicked(View v) {
        expr = new StringBuffer();

        TextView tv = (TextView)findViewById(R.id.tvAns);
        String s = tv.getText().toString();
        expr.append(s);
        updateExprDisplay();

        updateAnsDisplay("0");

    }

    public void ACClicked(View v) {
        //Clear expr and updateExprDisplay
        expr = new StringBuffer();
        updateExprDisplay();
        //Display a toast that the value is cleared
        Toast t = Toast.makeText(this.getApplicationContext(),
                "All cleared", Toast.LENGTH_SHORT);
        t.show();
    }

    public void BSClicked(View v) {
        //Remove the last character from expr, and updateExprDisplay
        if (expr.length() > 0) {
            expr.deleteCharAt(expr.length()-1);
            updateExprDisplay();
            recalculate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    int counter = 0;
//
//
//    public void incClicked(View v) {
//        counter++;
//        TextView tv = (TextView)findViewById(R.id.tvOutput);
//        tv.setText(Integer.toString(counter));
//    }
//
//    // set decClicked to the onClick property of Decrease button
//    public void decClicked(View v) {
//        counter--;
//        TextView tv = (TextView)findViewById(R.id.tvOutput);
//        tv.setText(Integer.toString(counter));
//    }
}
