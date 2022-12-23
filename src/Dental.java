/**
 * @param cdtCode represents CPT Code
 * @param procedures represents Dental Procedures
 * @param beginDate represents begin date DFS (Dental Fee Schedule) is valid
 * @param endDate represents end date DFS is valid
 * @param maxPayAmount represents estimate amount of payment
 */
public record Dental(String cdtCode,
                     String procedures,
                     String beginDate,
                     String endDate,
                     String maxPayAmount) {
}
