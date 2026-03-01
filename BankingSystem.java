import java.util.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

// Main Class
public class BankingSystem {

    static Scanner sc = new Scanner(System.in);

    // ===================== LOAN SECTION =====================
    static List<String[]> loanTypes = new ArrayList<>();

    static {
        loanTypes.add(new String[]{"Personal Loan", "10.5"});
        loanTypes.add(new String[]{"Home Loan", "7.0"});
        loanTypes.add(new String[]{"Car Loan", "8.5"});
        loanTypes.add(new String[]{"Education Loan", "6.5"});
        loanTypes.add(new String[]{"Business Loan", "12.0"});
    }

    static class LoanApplication {
        String name;
        String address;
        double creditScore;
        double income;
        String loanType;
        double interestRate;
        double loanAmount;
        int loanTerm;
        String schedule;

        public LoanApplication(String name, String address, double creditScore,
                               double income, String loanType, double interestRate,
                               double loanAmount, int loanTerm, String schedule) {
            this.name = name;
            this.address = address;
            this.creditScore = creditScore;
            this.income = income;
            this.loanType = loanType;
            this.interestRate = interestRate;
            this.loanAmount = loanAmount;
            this.loanTerm = loanTerm;
            this.schedule = schedule;
        }
    }

    static void applyLoan() {
        System.out.println("\n--- Loan Application ---");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Credit Score: ");
        double creditScore = sc.nextDouble();

        System.out.print("Enter Monthly Income: ");
        double income = sc.nextDouble();
        sc.nextLine();

        System.out.println("Available Loan Types:");
        for (String[] lt : loanTypes) {
            System.out.println("- " + lt[0]);
        }

        System.out.print("Enter Loan Type: ");
        String type = sc.nextLine();

        double rate = 0;
        boolean valid = false;

        for (String[] lt : loanTypes) {
            if (lt[0].equalsIgnoreCase(type)) {
                rate = Double.parseDouble(lt[1]);
                valid = true;
                break;
            }
        }

        if (!valid) {
            System.out.println("Invalid Loan Type!");
            return;
        }

        System.out.print("Enter Loan Amount: ");
        double amount = sc.nextDouble();

        System.out.print("Enter Loan Term (months): ");
        int term = sc.nextInt();
        sc.nextLine();

        System.out.print("Repayment Schedule: ");
        String schedule = sc.nextLine();

        LoanApplication app = new LoanApplication(name, address, creditScore, income,
                type, rate, amount, term, schedule);

        boolean approved = (creditScore >= 650 && income >= 2000);

        if (approved) {
            System.out.println("\nLoan Approved!");
            double emi = calculateEMI(app.loanAmount, app.interestRate, app.loanTerm);
            System.out.println("EMI: " + new DecimalFormat("#.##").format(emi));
        } else {
            System.out.println("\nLoan Rejected (Low Credit Score or Income)");
        }
    }

    static double calculateEMI(double principal, double annualRate, int months) {
        double r = annualRate / 12 / 100;
        return (principal * r * Math.pow(1 + r, months)) /
                (Math.pow(1 + r, months) - 1);
    }

    // ===================== FD SECTION =====================

    enum FDType {
        STANDARD, TAX_SAVING, CUMULATIVE, NON_CUMULATIVE
    }

    static class FixedDeposit {
        static int counter = 1;
        int fdId;
        int userId;
        FDType type;
        double principal;
        double rate;
        int tenure;
        boolean cumulative;
        Date startDate;
        Date maturityDate;

        FixedDeposit(int userId, FDType type, double principal,
                     double rate, int tenure, boolean cumulative) {
            this.fdId = counter++;
            this.userId = userId;
            this.type = type;
            this.principal = principal;
            this.rate = rate;
            this.tenure = tenure;
            this.cumulative = cumulative;
            this.startDate = new Date();

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, tenure);
            this.maturityDate = cal.getTime();
        }

        double calculateInterest() {
            double r = rate / 100;
            if (!cumulative) {
                return principal * r * tenure / 12;
            } else {
                double amount = principal * Math.pow(1 + r / 12, tenure);
                return amount - principal;
            }
        }

        double getMaturityAmount() {
            return principal + calculateInterest();
        }

        void display() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            DecimalFormat df = new DecimalFormat("#.##");

            System.out.println("FD ID: " + fdId);
            System.out.println("Principal: " + principal);
            System.out.println("Interest Earned: " + df.format(calculateInterest()));
            System.out.println("Maturity Amount: " + df.format(getMaturityAmount()));
            System.out.println("Maturity Date: " + sdf.format(maturityDate));
        }
    }

    static Map<Integer, FixedDeposit> fdRecords = new HashMap<>();

    static void createFD() {
        System.out.println("\n--- Create Fixed Deposit ---");

        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();

        System.out.println("Select FD Type:");
        System.out.println("1. Standard");
        System.out.println("2. Tax Saving");
        System.out.println("3. Cumulative");
        System.out.println("4. Non-Cumulative");
        int choice = sc.nextInt();

        FDType type;
        switch (choice) {
            case 1: type = FDType.STANDARD; break;
            case 2: type = FDType.TAX_SAVING; break;
            case 3: type = FDType.CUMULATIVE; break;
            case 4: type = FDType.NON_CUMULATIVE; break;
            default:
                System.out.println("Invalid Type");
                return;
        }

        System.out.print("Enter Principal Amount: ");
        double principal = sc.nextDouble();

        System.out.print("Enter Interest Rate: ");
        double rate = sc.nextDouble();

        System.out.print("Enter Tenure (months): ");
        int tenure = sc.nextInt();
        sc.nextLine();

        boolean cumulative = (type == FDType.CUMULATIVE);

        FixedDeposit fd = new FixedDeposit(userId, type, principal, rate, tenure, cumulative);
        fdRecords.put(fd.fdId, fd);

        System.out.println("FD Created Successfully! FD ID: " + fd.fdId);
    }

    static void viewFD() {
        System.out.print("Enter FD ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        FixedDeposit fd = fdRecords.get(id);
        if (fd != null) {
            fd.display();
        } else {
            System.out.println("FD not found!");
        }
    }

    // ===================== MAIN MENU =====================

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Apply for Loan");
            System.out.println("2. Create Fixed Deposit");
            System.out.println("3. View Fixed Deposit");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: applyLoan(); break;
                case 2: createFD(); break;
                case 3: viewFD(); break;
                case 4:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}