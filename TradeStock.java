import java.io.*;

public class TradeStock {
    static class ProfitResult {
        int buyDay;
        int sellDay;
        float profit;

        ProfitResult(int buyDay, int sellDay, float profit){
            this.buyDay = buyDay;
            this.sellDay = sellDay;
            this.profit = profit;
        }
    }

    /*
        Algorithm Number: 0
        Algorithm Type: Brute Force
        Algorithm Time Complexity: Theta(n^2)
     */
    public static ProfitResult findMaxProfit0(float[] prices) {
        int maxProfitBuyDay = Integer.MIN_VALUE;
        int maxProfitSellDay = Integer.MIN_VALUE;
        float maxProfit = Float.MIN_VALUE;
        for (int buyDay = 0; buyDay < prices.length - 1; buyDay++){
            for (int sellDay = buyDay + 1; sellDay < prices.length; sellDay++){
                float profit = prices[sellDay] - prices[buyDay];
                if (profit > maxProfit){
                    maxProfit = profit;
                    maxProfitBuyDay = buyDay;
                    maxProfitSellDay = sellDay;
                }
            }
        }
        return new ProfitResult(maxProfitBuyDay, maxProfitSellDay, maxProfit);
    }


    /*
        Algorithm Number: 1
        Algorithm Type: Divide and Conquer
        Algorithm Time Complexity: Theta(nlog(n))
     */

    public static ProfitResult findMaxProfit1(float[] prices) {
        return findMaxProfitHelper(prices, 0, prices.length - 1);
    }
    private static ProfitResult findMaxProfitHelper(float[] prices, int start, int end) {
        if ( start >= end) return new ProfitResult(start, end, 0);
        int mid = start + (end - start) / 2;

        // Conquer
        ProfitResult leftResult = findMaxProfitHelper(prices, start, mid);
        ProfitResult rightResult = findMaxProfitHelper(prices, mid+1, end);

        // Combine
        float minLeftPrice = Float.MAX_VALUE;
        int minPriceIndex = start;

        for (int i = start; i <= mid; i++) {
            if (prices[i] < minLeftPrice) {
                minLeftPrice = prices[i];
                minPriceIndex = i;
            }
        }

        float maxRightPrice = Float.MIN_VALUE;
        int maxPriceIndex = mid + 1;
        for (int i = mid + 1; i <= end; i++){
            if (prices[i] > maxRightPrice){
                maxRightPrice = prices[i];
                maxPriceIndex = i;
            }
        }

        float crossProfit = maxRightPrice - minLeftPrice;

        // Decide the maximum of the three profits
        if (leftResult.profit > rightResult.profit && leftResult.profit > crossProfit) {
            return leftResult;
        } else if (rightResult.profit > crossProfit){
            return rightResult;
        } else {
            return new ProfitResult(minPriceIndex, maxPriceIndex, crossProfit);
        }
    }

    /*
        Algorithm Number: 2
        Algorithm Type: Divide and Conquer
        Algorithm Time Complexity: Theta(n)
     */
    public static ProfitResult findMaxProfit2(float[] prices){
        if(prices.length < 2) return  new ProfitResult(-1,-1,0);

        int minPriceDay = 0;
        int maxProfitBuyDay = 0;
        int maxProfitSellDay = 1;
        float maxProfit = 0;
        float minPrice = prices[0];

        for (int day = 1; day < prices.length; day++){
            if (prices[day] - minPrice > maxProfit){
                maxProfit = prices[day] - minPrice;
                maxProfitBuyDay = minPriceDay;
                maxProfitSellDay = day;
            }

            if (prices[day] < minPrice){
                minPrice = prices[day];
                minPriceDay = day;
            }
        }

        return new ProfitResult(maxProfitBuyDay, maxProfitSellDay, maxProfit);
    }

    /*
        Algorithm Number: 3
        Algorithm Type: Decrease and Conquer
        Algorithm Time Complexity: Theta(n)
     */
    public static ProfitResult findMaxProfit3(float[] prices){
        if (prices.length <  2) return new ProfitResult(-1, -1, 0);

        int minPriceDay = 0;
        float maxProfit = 0;
        int maxProfitBuyDay = 0;
        int maxProfitSellDay = 1;

        for (int currentDay = 1; currentDay < prices.length; currentDay++){
            if (prices[currentDay] - prices[minPriceDay] > maxProfit){
                maxProfit = prices[currentDay] - prices[minPriceDay];
                maxProfitBuyDay = minPriceDay;
                maxProfitSellDay = currentDay;
            }

            if (prices[currentDay] < prices[minPriceDay]) minPriceDay = currentDay;
        }

        return new ProfitResult(maxProfitBuyDay, maxProfitSellDay, maxProfit);
    }

    /*
        Main Method
        - includes implementation of the args from the terminal and implements it.
        - added try catch block.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java TradeStock <file_name> <algorithm_type_number>");
            return;
        }
        
        String fileName = args[0];
        String algoNum = args[1];
        int size = 0;
        
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            int numberOfPrices = dis.readInt();
            float[] prices = new float[numberOfPrices];
    
            for (int i = 0; i < numberOfPrices; i++) {
                prices[i] = dis.readFloat();
                size++;
            }
            
            System.out.println("Visho Malla Oli");
            System.out.println(fileName);
            System.out.println("Input size = " + size);
            executeSelectedAlgorithm(algoNum, prices);
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
        }
    }
    
    private static void executeSelectedAlgorithm(String algoNum, float[] prices) {
        ProfitResult maxProfit = null;
        String algorithmType;

        long startTime = System.currentTimeMillis();
        switch (algoNum) {
            case "0":
                maxProfit = findMaxProfit0(prices);
                algorithmType = "Theta(n^2) Brute Force";
                break;
            case "1":
                maxProfit = findMaxProfit1(prices);
                algorithmType = "Theta(nlog(n)) Divide and Conquer";
                break;
            case "2":
                 maxProfit = findMaxProfit2(prices);
                algorithmType = "Theta(n) Divide and Conquer";
                break;
            case "3":
                 maxProfit = findMaxProfit3(prices);
                algorithmType = "Theta(n) Decrease and Conquer";
                break;
            default:;
                System.out.println("Invalid algorithm number. Usage: 0 <= <algorithm_type_number> <= 2");
                return;
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println(algorithmType);
        if (maxProfit != null) {
            System.out.println(maxProfit.buyDay + ", " + maxProfit.sellDay + ", " + maxProfit.profit);
        }
        System.out.println("Time taken in milli seconds: " + elapsedTime);
    }
}

