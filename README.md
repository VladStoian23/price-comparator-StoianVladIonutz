# price-comparator-StoianVladIonutz
Accesa summer intenship project "
useful :

public List<Discount> getDiscountsAddedInLast24Hours() {
        LocalDateTime cutoff = LocalDateTime.now().minusHours(24);
        LocalDate cutoffDate = cutoff.toLocalDate();
        return discounts.stream()
                .map(entry -> entry.discount)
                .filter(discount -> {
                    LocalDate startDate = discount.getStartDate();
                    // Show if startDate is today or yesterday (within last 24h)
                    return !startDate.isBefore(cutoffDate);
                })
                .collect(Collectors.toList());
    }
