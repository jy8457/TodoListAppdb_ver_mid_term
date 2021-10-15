package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println("To do List 어플사용법");
        System.out.println();
        System.out.println("1. [항목 추가] ( add )");
        System.out.println("2. [항목 삭제] ( del )");
        System.out.println("3. [항목 수정]  ( edit )");
        System.out.println("4. [전체 목록] ( ls )");
        System.out.println("5. [제목 이름순 정렬] ( ls_name )");
        System.out.println("6. [제목 이름 역순 정렬] ( ls_name_desc )");
        System.out.println("7. [날짜순 정렬] ( ls_date )");
        System.out.println("8. [날짜역순 정렬] ( ls_date_desc )");
        System.out.println("9. [키워드 찾기] ( find )");
        System.out.println("10. [카테고리 찾기] ( find_cate )");
        System.out.println("11. [카테고리 정렬] ( ls_cate )");
        System.out.println("12. [완료 표시] ( comp )");
        System.out.println("13. [완료된 항목표시] ( ls_comp )");
        System.out.println("14. [평점별 정렬] ( ls_rate )");
        System.out.println("15. [볼거리 추천] ( recom )");
        System.out.println("16. [종료] (Or press escape key to exit)");
        //System.out.println("원하는 메뉴를 선택하세요 >");
    }
    public static void prompt()
    {
    	System.out.println();
    	System.out.println("원하시는 명령어를 입력해주세요.");
    }
    
}
