import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        final String url = "jdbc:mysql://localhost:3306/barasserie";//fill with yours
        final String rootName = "root";//fill with yours
        final String password = "root";//fill with yours
        String sqlInstruction;
        Scanner sc = new Scanner(System.in);
        String email =sc.nextLine();
        String passwordUser = sc.nextLine();
        sc.close();
        try {
                Connection connection = DriverManager.getConnection(url, rootName, password);
                sqlInstruction = "SELECT * FROM communication WHERE communicationDetails = ?";
                PreparedStatement statement = connection.prepareStatement(sqlInstruction);
                statement.setString(1, email);
                ResultSet rs = statement.executeQuery();
                rs = Utils.checkingResultSet(rs);
                if (rs != null)
                {
                    sqlInstruction = "SELECT * FROM bussiness_entity WHERE id = ?";
                    statement = connection.prepareStatement(sqlInstruction);
                    statement.setInt(1, rs.getInt("entity"));
                    rs = statement.executeQuery();
                    rs = Utils.checkingResultSet(rs);
                    if (rs != null)
                    {
                        if (rs.getString("hashedPassword").equals
                        (Utils.hashPassword(passwordUser,rs.getString("salt"))))
                            {
                                System.out.println("Authenticated");
                            }
                            else
                            {
                                System.out.println("mail or password incorrect");
                            }
                         
                    }
                    else
                    {
                        System.out.println("mail or password incorrect");
                    }
                }
                else
                {
                    System.out.println("mail or password incorrect");
                }
        } catch (Exception e) {
             System.out.println("Error is: " + e.getMessage());
        }
    }
    

}
