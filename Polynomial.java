import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
public class Polynomial{
	//i Field
	double[] Coefficients;
	int[] Exponents;
	//ii
	public Polynomial(){
		Coefficients = new double[]{0};
		Exponents = new int[]{0};
	}
	//iii
	public Polynomial(double[] poly, int[] expo){
		Coefficients = poly;
		Exponents = expo;
	}
	public Polynomial(File f) throws IOException{
		BufferedReader new_file = new BufferedReader(new FileReader(f));
		String line = new_file.readline();
		new_file.close();
		String[] term = line.split("[-+]");
		double[] coefficients = new double[term.length];
		int[] exponents = new int[term.length];
		int count = 0;
		for(int i = 0; i < term.length; i++){
			String[] term2 = term[i].split("x");
			coefficients[i] = Double.parseDouble(term2[0]);
			if(term2.length > 1){
				exponents[i] = Integer.parseInt(term2[1]);
			}
			else{
				exponents[i] = 0;
			}

		}
	}
	//iv
	public Polynomial add(Polynomial x){
		int[] temp_expo = new int[]{0};
		double[] temp_coeff = new double[]{0};
		int count = 0;
		for(int i=0; i < Exponents.length; i++){
			temp_expo[i] = Exponents[i];
			temp_coeff[i] = Coefficients[i];
		}
		for(int j=0; j < x.Exponents.length; j++){
			int boo = 1;
			for(int m=0; m < Exponents.length; m++){
				if(Exponents[m]== x.Exponents[j]){
					temp_coeff[m] = Coefficients[m]+ x.Coefficients[j];
					boo = 0;
				}
			}
			if (boo == 1){
				count ++;
				temp_expo[Exponents.length + count -1] = x.Exponents[j];
				temp_coeff[Exponents.length + count -1] = x.Coefficients[j];
			}
		}
		//remove redundant 0s
		int count0s = 0;
		int[] temp_expo2;
		double[] temp_coeff2;
		for(int n = 0; n < temp_expo.length; n++){
			temp_expo2 = new int[]{0};
			temp_coeff2 = new double[]{0};
			while(count0s < 2){
				temp_expo2[n] = temp_expo[n];
				temp_coeff2[n] = temp_coeff[n];
			}
			if(temp_expo[n] == 0){
				count0s ++;
			}
		}
		Polynomial c1 = new Polynomial(temp_coeff2, temp_expo);
		return c1;
	}
	public double evaluate(double x){
		double sum = 0;
		for(int i=0; i< Coefficients.length; i++){
			sum += Coefficients[i] * (Math.pow(x,Exponents[i]));
		}
		return sum;
	}
	public boolean hasRoot(double x){
		if(evaluate(x) == 0){
			return true;
		}
		return false;
	}
	public Polynomial multiply(Polynomial p){
		int[] expo = new int[Coefficients.length * p.Coefficients.length];
		double[] coeff = new double[Coefficients.length * p.Coefficients.length];
		int m = 0;		
		for(int i = 0; i < Coefficients.length; i++){
			for(int j =0; j< p.Coefficients.length; j++){
				coeff[m] = Coefficients[i] * p.Coefficients[j];
				expo[m] = Exponents[i] + p.Exponents[j];
				m++;
			}
		}
		//simplify
		for(int i=0; i < expo.length; i++){
			for(int j = i + 1; j < expo.length; j++){
				if(expo[i] == expo [j]){
					coeff[i] = coeff[i] + coeff[j];
					coeff[j] = 0;
				}
			}
		}
		//remove redundant 0s
		int count = 0;
		int[] temp_expo = new int[]{0};
		double[] temp_coeff = new double[]{0};
		for(int i=0; i< expo.length; i++){
			if(coeff[i] != 0){
				temp_expo[count] = expo[i];
				temp_coeff[count] = coeff[i];
				count++;
			}
		}
		return new Polynomial(temp_coeff, temp_expo);
	}
	public void saveToFile(String f) throws FileNotFoundException{
		BufferedWriter new_file = new BufferedWriter(new FileWriter(f));
		for(int i = 0; i < Coefficients.length; i++){
			if(Coefficients[i] >= 0 ){
				new_file.write("+");
			}
			new_file.write(String.valueOf(Coefficients[i]));
			if(Exponents[i] > 0 ){
				new_file.write("x");
				if(Exponents[i]> 1){
					new_file.write(String.valueOf(Exponents[i]));
				}
			}
		}
		new_file.close();
	}
}