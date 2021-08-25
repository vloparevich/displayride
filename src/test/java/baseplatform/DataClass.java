package baseplatform;

import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;


public class DataClass {


    public static String firstNameGenerator() {

        Faker faker = new Faker();
        String firstName = faker.name().firstName();

        return firstName;
    }

    public static String lastNameGenerator() {

        Faker faker = new Faker();
        String lastName = faker.name().lastName();

        return lastName;
    }

    public static String fullNameGenerator() {

        Faker faker = new Faker();
        String name = faker.name().fullName();
        String streetAddress = faker.address().streetAddress();
        return name;
    }

    public static String addressGenerator() {

        Faker faker = new Faker();
        String streetAddress = faker.address().streetAddress();
        return streetAddress;
    }

    @DataProvider(name = "riderName-Provider")
    public static Object[][] dataProviderMethod() {

        return new Object[][]{

                {"John Smith"},
                {"Jessica Alba"},
                {"Mister Anderson"},
                {"Director"}

        };
    }

    @DataProvider(name = "riderName-Provider-2")
    public static Object[][] dataProviderMethod_2() {

        Object[][] firstName = new Object[1][1];

        firstName[0][0] = firstNameGenerator();
        return firstName;

    }


    @DataProvider(name = "riderName-Provider-3")
    public static Object[][] dataProviderMethod_3() {

        return new Object[][]{

                {"Test AbCd"},
                {"さよなら"},
                {"12356"},
                {"@#$%@#$%@#$%@#$%"},
                {"\uD83C\uDFBA\uD83C\uDFBC\uD83C\uDFD3\uD83C\uDFF8⛳️"},
                {"John Smithさよなら12356\uD83C\uDFBA\uD83C\uDFBC\uD83C\uDFD3\uD83C\uDFF8⛳@#$%@#$%@#$%@#$%"},
                {"Testing123Testing123Testing123Testing123Testing123Testing1234567"}


        };
    }

    @DataProvider(name = "riderName-Provider-4")
    public static Object[][] dataProviderMethod_4() {

        Object[][] riderNameMoreThan64CharsValue = new Object[1][1];

        riderNameMoreThan64CharsValue[0][0] = "Testing123Testing123Testing123Testing123Testing123Testing1234567Testing";
        return riderNameMoreThan64CharsValue;

    }


    @DataProvider(name = "FTP_Images_Provider")
    public static Object[][] dataProviderMethod_5() {

        return new Object[][] {

                {"qa/DisplayRide.jpeg", 60},
                {"uber/1.png", 60},
                {"coopcab/2.png", 60},
                {"yandex/3.png", 60},
                {"lyft/4.png", 60},
                {"ride/5.png", 60},
        };
    }


    @DataProvider(name = "FTP_Video_Provider")
    public static Object[][] dataProviderMethod_6() {

        return new Object[][] {

                {"uber/uber-deal-demo.mp4", 90},
                {"uber/uber-color-animation.mp4", 120},
        };
    }

    @DataProvider(name = "FTP_Demo_Provider")
    public static Object[][] dataProviderMethod_7() {

        return new Object[][] {

                {"uber/uberdemofast.txt", 90},
                {"qa/demoMultiLang.txt", 120},
        };
    }

    //Added by Shuna for Car Service Testing
    @DataProvider(name = "carServiceName-1")
    public static Object[][] dataProviderMethod_10() {

        return new Object[][]{

                {"Test AbCd"},
                {"さよなら"},
                {"12356"},
                {"@#$%@#$%@#$"},
                {"\uD83C\uDFBA\uD83C\uDFBC\uD83C\uDFD3\uD83C\uDFF8⛳️"},
                {"Joさ12\uD83C\uDFBA⛳@"},
                {"Testing12356"}


        };
    }

    //Added by Shuna for Car Service Testing
    @DataProvider(name = "carServiceName-2")
    public static Object[][] dataProviderMethod_8() {

        Object[][] carServiceProviderNameMoreThan12CharsValue = new Object[1][1];

        carServiceProviderNameMoreThan12CharsValue[0][0] = "Testing12356Testing";
        return carServiceProviderNameMoreThan12CharsValue;

    }

    //Added by Shuna for Car Service Testing
    @DataProvider(name = "carServiceName-3")
    public static Object[][] dataProviderMethod_9() {

        return  new Object[][]{
                {"Uber"},
                {"Lyft"},
                {"Ola"},
                {"DiDi"},
                {"Curb"},
                {"Gett"},
                {"Careem"},
                {"Yandex"},
                {"Grab"},
                {"InstaRyde"},
                {"Taxify"},
                {"Cabify"},
                {"EasyTaxi"},
                {"GoCatch"}
        };

    }
}






