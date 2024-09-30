package part2;

import part1.common.JdbcUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class JdbcCurdTest {
    public static void main(String[] args) {
        JdbcCrud jc = new JdbcCrud();
        //회원정보 키보드로 입력
//        Members mb=getMemberInfo();
//        boolean result=jc.join(mb);  //insert into
//        if(result) {
//            System.out.println("회원가입 성공");
//        }else{
//            System.out.println("회원가입 실패");
//        }
//
//        ArrayList<Members> mbList=jc.select();
//        //for each
//        for (Members member: mbList){
//            //System.out.println("아이디:"+id);
//            //member.showInfo();  //아이디, 이름,나이, 성별
//            System.out.println(member); //toString 재정의
//        }
//        for (int i = 0; i < mbList.size(); i++) {
//            //mbList.get(i).showInfo();
//            System.out.println(mbList.get(i)); //toString
//        }
        //============ 수정 ===========================
//        System.out.print("수정할 아이디 입력:");
//        String id=JdbcUtil.SC.next();
//        System.out.print("수정할 컬럼명 입력:");
//        String colName=JdbcUtil.SC.next();  //pw or name or gender
//        System.out.print("수정할 컬럼값 입력:");
//        String colValue=JdbcUtil.SC.next();  //1234
//        Members mb=jc.memberUpdate(id,colName,colValue);
//        if(mb!=null){
//            System.out.println("수정 성공");
//            System.out.println(mb);
//        }else{
//            System.out.println("수정 실패");
//        }
        //-=============  삭제 ==============
        System.out.print("삭제할 아이디 입력:");
        String id = JdbcUtil.SC.next();
        System.out.print("삭제할 비번 입력:");
        String pw = JdbcUtil.SC.next();
        boolean result = jc.memberDelete(id, pw);
        if (result) {
            System.out.println("삭제 성공");
            ArrayList<Members> list = jc.select();//확인
            //System.out.println("list:"+list);  //ArrayList의 toString()-->Members의 toString()
            for (Members mb:list){
                System.out.println(mb); //Members의 toString()
            }
        } else {
            System.out.println("삭제 실패");
        }

//        System.out.println("로그인 될때까지 무한루프 실행");
//        while(true){
//            System.out.print("아이디 입력:");
//            String id=JdbcUtil.SC.next();
//            System.out.print("비번 입력:");
//            String pw=JdbcUtil.SC.next();
//            ArrayList<Members> list=jc.login(id,pw);
//            if(list!=null && list.size()!=0){
//                //로그인 성공
//                for(Members mb: list){
//                    //mb.showInfo();
//                    System.out.println(mb); //Members의 toString
//                }
//                break;
//            }else{
//                System.out.println("로그인 실패");
//            }
//        }//while end
        //회원 수정
        System.out.println("프로그램 종료");
    }

    private static Members getMemberInfo() {
        Members mb = new Members();
        System.out.print("아이디 입력:");
        mb.setId(JdbcUtil.SC.next());
        System.out.print("비번 입력:");
        mb.setPw(JdbcUtil.SC.next());
        System.out.print("이름 입력:");
        mb.setName(JdbcUtil.SC.next());
        System.out.print("나이 입력:");
        mb.setAge(JdbcUtil.SC.nextInt());
        System.out.print("성별 입력:");
        mb.setGender(JdbcUtil.SC.next());
        return mb;
    }
}
