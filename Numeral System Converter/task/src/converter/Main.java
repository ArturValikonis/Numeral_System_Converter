package converter;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int srcRadix = scanner.nextInt();
            if (srcRadix < 1 || srcRadix > 36) {
                throw new WrongRadixException("radix < 1 or radix > 36");
            }
            String srcNumber = scanner.next();
            int trgRadix = scanner.nextInt();
            if (trgRadix < 1 || trgRadix > 36) {
                throw new WrongRadixException("radix < 1 or radix > 36");
            }

            System.out.println(Converter.convertNumber(srcRadix, srcNumber, trgRadix));
        } catch (InputMismatchException e) {
            System.out.println("Error: a number was expected");
        } catch (WrongRadixException e) {
            System.out.println("Error: the radix should be from 1 to 36");
        } catch (NumberFormatException e) {
            System.out.println("Error: number is impossible");
        } catch (NoSuchElementException e) {
            System.out.println("Error: empty input");
        }
    }
}

class WrongRadixException extends RuntimeException {
    public WrongRadixException(String s) {
        super(s);
    }
}
