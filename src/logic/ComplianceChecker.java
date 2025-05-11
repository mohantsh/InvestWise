package logic;

import java.util.*;
import model.Asset;

/**
 * Provides compliance checking functionality for investment portfolios.
 * This class implements various rules to ensure investment portfolios meet
 * certain criteria for diversification and minimum investment amounts.
 */
public class ComplianceChecker {
    /**
     * Checks a list of assets against compliance rules and returns a report.
     * The rules checked are:
     * 1. Total portfolio value must be at least 1000
     * 2. No single asset type can exceed 70% of total portfolio value
     * 3. Portfolio must have at least 2 different asset types
     *
     * @param assets the list of assets to check
     * @return a list of strings containing the compliance check results
     */
    public static List<String> checkCompliance(List<Asset> assets) {
        List<String> results = new ArrayList<>();
        if (assets.isEmpty()) {
            results.add("No assets in portfolio.");
            return results;
        }

        double totalValue = assets.stream().mapToDouble(Asset::getValue).sum();
        if (totalValue < 1000) results.add("Rule 1 Failed: Total value < 1000.");
        else results.add("Rule 1 Passed.");

        Map<String, Double> typeTotals = new HashMap<>();
        for (Asset a : assets) {
            typeTotals.put(a.getType(), typeTotals.getOrDefault(a.getType(), 0.0) + a.getValue());
        }

        for (var entry : typeTotals.entrySet()) {
            if ((entry.getValue() / totalValue) * 100 > 70) {
                results.add("Rule 2 Failed: " + entry.getKey() + " > 70%.");
            }
        }

        if (typeTotals.size() < 2) {
            results.add("Rule 3 Failed: Less than 2 asset types.");
        }

        if (results.stream().noneMatch(r -> r.contains("Failed"))) {
            results.clear();
            results.add("All compliance rules passed ✅.");
        }

        return results;
    }
}
