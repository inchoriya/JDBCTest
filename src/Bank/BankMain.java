package Bank;

import java.io.Console;
import java.util.Scanner;

public class BankMain {

    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int menu = 0;

        String cAccount;

        Client client = new Client();
        BankSQL sql = new BankSQL();

        System.out.println("ICIA 은행에 오신 것을 환영합니다.");
        System.out.println("무엇을 도와드릴까요?");

        sql.connect();

        while(run) {
            System.out.println("===============================");
            System.out.println("[1]생성\t\t[2]입급\t\t[3]출금");
            System.out.println("[4]송금\t\t[5]조회\t\t[6]종료");
            System.out.println("===============================");

            System.out.print("선택 >> ");
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    // 고객번호(자동)
                    // int cNum = sql.clientNumber();
                    // System.out.println("고객번호 확인 : " +  cNum);
                    client.setcNum(sql.clientNumber());

                    // 고객이름(작성)
                    System.out.print("고객이름 >> ");
                    client.setcName(sc.next());

                    // 계좌번호(자동)
                    // String cAccount = sql.accountNumber();
                    // System.out.println("계좌번호 조회 : " + cAccount);
                    client.setcAccount(sql.accountNumber());

                    // 잔액 : 0원
                    client.setBalance(0);

                    sql.createAccount(client);

                    break;
                case 2:
                    // 입금할 계좌번호 입력해서 존재 유무 확인
                    System.out.print("계좌번호 입력 >> ");
                    cAccount = sc.next();
                    boolean checked = sql.checkAccount(cAccount);

                    if(checked){
                        System.out.print("입금액 >> ");
                        int amount = sc.nextInt();

                        sql.deposit(cAccount, amount);
                        System.out.println("입금 성공!");

                    } else {
                        System.out.println("계좌가 존재하지 않습니다.");
                    }

                    break;
                case 3:
                    // 입금할 계좌번호 입력해서 존재 유무 확인
                    System.out.print("계좌번호 입력 >> ");
                    cAccount = sc.next();
                    checked = sql.checkAccount(cAccount);

                    if(checked){
                        System.out.print("출금액 >> ");
                        int amount = sc.nextInt();

                        // 해당 계좌의 잔액 확인
                        int balance = sql.checkBalance(cAccount);

                        if(balance >= amount){
                            sql.withdraw(cAccount, amount);
                            System.out.println("출금 성공!");
                        } else {
                            System.out.println("잔액이 " + (amount-balance) + "원 부족합니다.");
                        }
                    } else {
                        System.out.println("계좌가 존재하지 않습니다.");
                    }
                    break;
                case 4:
                    System.out.print("보내는 분 계좌 >> ");
                    String sAccount = sc.next();

                    if(sql.checkAccount(sAccount)){
                        System.out.print("받는 분 계좌 >> ");
                        String rAccount = sc.next();

                        if(sql.checkAccount(rAccount)){
                            System.out.print("송금할 금액 >> ");
                            int amount = sc.nextInt();

                            int balance = sql.checkBalance(sAccount);

                            if(balance >= amount){
                                sql.withdraw(sAccount, amount);
                                sql.deposit(rAccount, amount);
                                System.out.println("송금 성공!");
                                // 3333-52-1108618
                                // 3333-68-3285102
                            } else {
                                System.out.println("잔액이 " + (amount-balance) + "원 부족합니다.");
                            }

                        } else {
                            System.out.println("받는 분 계좌가 정확하지 않습니다.");
                        }

                    } else {
                        System.out.println("보내는 분 계좌가 정확하지 않습니다.");
                    }



                    break;
                case 5:
                    System.out.print("계좌번호 입력 >> ");
                    cAccount = sc.next();
                    checked = sql.checkAccount(cAccount);

                    if(checked){
                        int balance = sql.checkBalance(cAccount);
                        System.out.println("현재 잔액 : " + balance + "원");
                    } else {
                        System.out.println("계좌가 존재하지 않습니다.");
                    }
                    break;
                case 6:
                    System.out.println("이용해 주셔서 감사합니다.");
                    run = false;
                    sql.conClose();
                    break;
                default:
                    System.out.println("다시 입력하세요");
                    break;
            }
        }






    }
}
