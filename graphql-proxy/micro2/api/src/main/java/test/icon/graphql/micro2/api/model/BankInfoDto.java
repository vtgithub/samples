package test.icon.graphql.micro2.api.model;

public class BankInfoDto {
    private int personId;
    private int bankId;
    private String bankName;
    private int bankBranchCode;
    private String bankBranch;

    public BankInfoDto(int personId, int bankId, String bankName, int bankBranchCode, String bankBranch) {
        this.personId = personId;
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankBranchCode = bankBranchCode;
        this.bankBranch = bankBranch;
    }

    public BankInfoDto() {
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchCode(int bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }
}
