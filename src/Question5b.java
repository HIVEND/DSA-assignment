import java.util.Arrays;

public class Question5b {
    public static int batteryReplacement(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        Arrays.sort(serviceCenters, (a, b) -> Integer.compare(a[0], b[0])); // Sort service centers by distance from source city
        int batteryCount = 0;
        int currentMiles = startChargeCapacity;
        int index = 0;
        while (currentMiles < targetMiles) {
            int maxMiles = 0;
            int nextIndex = -1;
            // Look for the service center with the maximum miles offered
            for (int i = index; i < serviceCenters.length; i++) {
                if (serviceCenters[i][0] > currentMiles) {
                    break; // We cannot reach this service center with our current charge
                }
                if (serviceCenters[i][1] > maxMiles) {
                    maxMiles = serviceCenters[i][1];
                    nextIndex = i;
                }
            }
            if (nextIndex == -1) {
                return -1; // It is impossible to reach the target without battery replacements
            }
            currentMiles += maxMiles;
            index = nextIndex + 1;
            batteryCount++;
        }
        return batteryCount;
    }

    public static void main(String[] args) {
        int service[][] ={ {10,60},{20,30},{30,30},{60,40}};
        int target= 100;
        int ChargeCapacity = 10;
        System.out.println(batteryReplacement(service,target,ChargeCapacity));
    }

}