//vanshika singhal Project (Java)///
RIT ROORKEE 





import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class CallAnalytics {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/callcenter";
        String user = "username";
        String password = "password";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            // Hour of the day when the call volume is highest
            ResultSet rs = stmt.executeQuery("SELECT HOUR(start_time) as hour, COUNT(*) as count FROM call GROUP BY hour ORDER BY count DESC LIMIT 1");
            if (rs.next()) {
                System.out.println("Hour of the day when the call volume is highest: " + rs.getInt("hour") + "-" + (rs.getInt("hour")+1) + " AM");
            }

            // Hour of the day when the calls are longest
            rs = stmt.executeQuery("SELECT HOUR(start_time) as hour, SUM(duration) as duration FROM call GROUP BY hour ORDER BY duration DESC LIMIT 1");
            if (rs.next()) {
                System.out.println("Hour of the day when the calls are longest: " + rs.getInt("hour") + "-" + (rs.getInt("hour")+1) + " AM");
            }

            // Day of the week when the call volume is highest
            rs = stmt.executeQuery("SELECT DAYOFWEEK(start_time) as day, COUNT(*) as count FROM call GROUP BY day ORDER BY count DESC LIMIT 1");
            if (rs.next()) {
                DayOfWeek day = DayOfWeek.of(rs.getInt("day"));
                System.out.println("Day of the week when the call volume is highest: " + day.toString());
            }

            // Day of the week when the calls are longest
            rs = stmt.executeQuery("SELECT DAYOFWEEK(start_time) as day, SUM(duration) as duration FROM call GROUP BY day ORDER BY duration DESC LIMIT 1");
            if (rs.next()) {
                DayOfWeek day = DayOfWeek.of(rs.getInt("day"));
                System.out.println("Day of the week when the calls are longest: " + day.toString());
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
