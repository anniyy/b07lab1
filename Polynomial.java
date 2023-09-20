public class Polynomial{
	//i Field
	double[] Coefficients;
	//ii
	public Polynomial(){
		Coefficients = new double[]{0};
	}
	//iii
	public Polynomial(double[] poly){
		Coefficients = poly;
	}
	//iv
	public Polynomial add(Polynomial x){
		Polynomial c1 = new Polynomial();
		if(x.Coefficients.length <= Coefficients.length){
			for(int i=0; i < Coefficients.length; i++){
				if(i < x.Coefficients.length){
					c1.Coefficients[i] = Coefficients[i] + x.Coefficients[i];
				}
				c1.Coefficients[i] = 0 + Coefficients[i];
			}
			return c1;
		}
		else{
			Polynomial c2 = new Polynomial(x.Coefficients);
			for(int j=0; j < Coefficients.length; j++){
					c2.Coefficients[j] = Coefficients[j] + x.Coefficients[j];
			}
			return c2;
		}
	}
	public double evaluate(double x){
		double sum = 0;
		for(int i=0; i< Coefficients.length; i++){
			sum += Coefficients[i] * (Math.pow(x,i));
		}
		return sum;
	}
	public boolean hasRoot(double x){
		if(evaluate(x) == 0){
			return true;
		}
		return false;
	}
}