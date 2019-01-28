package test.icon.graphql.graphqltest.dto;

import test.icon.graphql.micro1.api.model.PersonDto;
import test.icon.graphql.micro2.api.model.BankInfoDto;

public class Person {
    private PersonDto personInfo;
    private BankInfoDto bankInfo;

    public PersonDto getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonDto personInfo) {
        this.personInfo = personInfo;
    }

    public BankInfoDto getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfoDto bankInfo) {
        this.bankInfo = bankInfo;
    }
}
