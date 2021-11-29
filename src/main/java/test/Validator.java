package test;

public class Validator {
    public static boolean isValidDate(Employee employee) {
        try {
            if (employee.getX() != null && employee.getY() != null && employee.getR() != null) {
                if (employee.getX().length() > 10) {
                    employee.setX(employee.getX().substring(0, 10));
                }
                if (employee.getY().length() > 10) {
                    employee.setY(employee.getY().substring(0, 10));
                }
                if (employee.getR().length() > 10) {
                    employee.setR(employee.getR().substring(0, 10));
                }
                double x = Double.parseDouble(employee.getX().replace(",", "."));
                double y = Double.parseDouble(employee.getY().replace(",", "."));
                double r = Double.parseDouble(employee.getR().replace(",", "."));
                if (isValid(x, y, r)) {
                    employee.setX(String.valueOf(x));
                    employee.setY(String.valueOf(y));
                    employee.setR(String.valueOf(r));
                    employee.setResult(checkArea(x, y, r) ? "Да" : "Нет");
                    return true;
                }
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean checkArea(double x, double y, double r) {
        return (checkRectangle(x, y, r) || checkTriangle(x, y, r) || checkCircle(x, y, r));
    }

    public static boolean checkRectangle(double x, double y, double r) {
        return x >= 0 && y >= 0 && x <= r && y <= r;
    }

    public static boolean checkTriangle(double x, double y, double r) {
        return x >= 0 && y <= 0 && y >= -r / 2 && (y >= -(r - x) / 2) && x <= r;
    }

    public static boolean checkCircle(double x, double y, double r) {
        return x <= 0 && y <= 0 && x * x + y * y <= r * r;
    }

    public static boolean isValid(double x, double y, double r) {
        return (x >= -5 && x <= 3) && (y >= -5 && y <= 3) && (r >= 1 && r <= 3);
    }
}

