package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); // 스태틱으로 선언(인스턴스는 한개만 존재)

    public static SingletonService getInstance() { // 인스턴스는 이거로만 호출할 수 있다
        return instance;
    }
    private SingletonService() { // 다른곳에서 new SingletonService()를 사용하지 못하게 프라이빗으로 생성자 생성
    }
    // 결국 인스턴스는 딱 한개밖에 생성이 안되고 그 생성된 인스턴스 한개는 getInstance() 로만 조회할 수 있기때문에 싱글톤 완성~
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");

    }
}
