package converter;

public class Converter {

    public static String convertNumber(Integer srcRadix, String srcNumber, Integer trgRadix) {
        if (srcNumber.contains(".")) {
            if (srcNumber.split("\\.")[1].equals("0")) {
                return integerConversion(srcRadix, srcNumber.split("\\.")[0], trgRadix);
            }
            return doubleConversion(srcRadix, srcNumber, trgRadix);
        } else return integerConversion(srcRadix, srcNumber, trgRadix);
    }

    private static String integerConversion(Integer srcRadix, String srcNumber, Integer trgRadix) {
        long number;
        if (srcRadix == 1) {
            number = srcNumber.length();
        } else {
            number = Long.parseLong(srcNumber, srcRadix);
        }

        String outputNumber = "";
        if (trgRadix == 1) {
            for (int i = 0; i < number; i++) {
                outputNumber += "1";
            }
        } else {
            outputNumber = Long.toString(number, trgRadix);
        }
        return outputNumber;
    }

    private static String doubleConversion(Integer srcRadix, String srcNumber, Integer trgRadix) {
        if (trgRadix == 1) {
            return integerConversion(srcRadix, srcNumber.split("\\.")[0], trgRadix);
        }
        String[] parts = srcNumber.split("\\.");
        String fractionPart = fractionPartConversion(srcRadix, parts[1], trgRadix);
        if (!fractionPart.equals("")) {
            return integerConversion(srcRadix, parts[0], trgRadix) + "." + fractionPart;
        } else return integerConversion(srcRadix, parts[0], trgRadix);
    }

    private static String fractionPartConversion(Integer srcRadix, String srcNumber, Integer trgRadix) {
        Double number = 0d;
        int counter = 1;

        for (char ch : srcNumber.toCharArray()) {
            number += (double) Long.parseLong(String.valueOf(ch), srcRadix) / Math.pow(srcRadix, counter);
            counter++;
        }

        String outputNumber = "";
        for (int i = 0; i < 5; i++) {
            number *= trgRadix;
            if (number >= 1) {
                outputNumber += Long.toString(number.intValue(), trgRadix);
                number -= (double) number.intValue();
            } else outputNumber += 0;
        }
        return outputNumber;
    }
}
