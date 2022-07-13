import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Land and Buildings Transaction Tax (LBTT) is  applied to land or property purchases,
 * both residential and non-residential. Scotland
 * CIRCUMSTANCES OF THE BUYER:
 * currently owns a property and lives in it as their main residence
 * Does not conduct any kind of business activity from their house
 * Does not own any other properties
 * At the end of the purchase the buyer will still only own one house
 * FOR 1ST TIME BUYERS WITH 1 HOME:
 * --Property value	        --LBTT rate
 * Up to £175,000	            0%
 * £175,001 to £250,000	    2%
 * £250,001 to £325,000	    5%
 * £325,001 to £750,000	    10%
 * £750,001 +	                12%
 */
public class LBTT {
    private List<Band> bandsList;

    /**
     * Instantiates a new LBTT object.
     */
    public LBTT() {
        this.bandsList = new ArrayList<>();

        try {
            File file = new File("LBTT.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            String[] lineArray;

            while ((line = br.readLine()) != null) {
                lineArray = line.split(","); // An array of {key boundary, percentage}
                this.bandsList.add(new Band(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1])));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates tax.
     *
     * @param propertyValue the property value
     * @return the tax value
     */
    public double calculate(int propertyValue){
        int currentValue = propertyValue;
        double tax = 0.00;
        float taxableSum;

        for(int i=0; i<this.bandsList.size(); i++) {
            taxableSum = (i>0) ? this.bandsList.get(i).getKeyBoundary() - this.bandsList.get(i-1).getKeyBoundary() : this.bandsList.get(i).getKeyBoundary();

            if(currentValue <= taxableSum || i == this.bandsList.size()-1) {
                tax += currentValue * ((float)this.bandsList.get(i).getPercentage()/100);
                break;
            }
            else {
                if (i == 0) {
                    currentValue -= (this.bandsList.get(i).getKeyBoundary());
                }
                else {
                    tax += taxableSum * ((float)this.bandsList.get(i).getPercentage()/100);
                    currentValue -= taxableSum;
                }
            }
        }

        BigDecimal bd = new BigDecimal(tax);
        return Double.parseDouble(String.valueOf(bd.setScale(2, RoundingMode.HALF_UP)));//String.format("%.2f", tax);//Float.parseFloat(String.valueOf(bd.setScale(2, RoundingMode.UP)));
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        LBTT tax = new LBTT();
        System.out.println(tax.calculate(750001));
        System.out.println(tax.calculate(890001));
    }

//    public float calculate(int iteration, float propertyValue) {
//        DecimalFormat df = new DecimalFormat("#.##");
//        df.setRoundingMode(RoundingMode.CEILING);
//
//        switch(iteration) {
//            case 1:
//                if(propertyValue <= 175000) {
//                    return 0.00F;
//                }
//                else {
//                    return calculate(2, propertyValue-175000); //Up to £175,000
//                }
//            case 2:
//                if(propertyValue <= 75000) {
//                    return Float.parseFloat(df.format((float) (propertyValue * 0.02)));
//                }
//                else {
//                    return 1500 + calculate(3, propertyValue-75000); //£175,001 to £250,000
//                }
//            case 3:
//                if(propertyValue <= 75000) {
//                    return Float.parseFloat(df.format((float) (propertyValue * 0.05)));
//                }
//                else {
//                    return 3750 + calculate(4, propertyValue-75000); //£250,001 to £325,000
//                }
//            case 4:
//                if(propertyValue <= 425000) {
//                    return Float.parseFloat(df.format((float) (propertyValue * 0.1)));
//                }
//                else {
//                    return 42500 + calculate(5, propertyValue-425000); //£325,001 to £750,000
//                }
//            default:  return Float.parseFloat(df.format((float) (propertyValue * 0.12))); //£750,001 +
//        }
//    }
//
//    public static void main(String[] args) {
//
//        LBTT tax = new LBTT();
//        System.out.println(tax.calculate(1,500000)); // everything should start with an iteration value of 1
//    }
}
