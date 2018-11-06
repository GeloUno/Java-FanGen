import java.util.Scanner;


class Fan {
    private String fan;

    public String getFan() {
        return fan;
    }

    public void setFan(String fan) {
        this.fan = fan;
    }

    protected int heightFan;
    protected char[][] fanChar = null;

    public int getHeightFan() {
        return heightFan;
    }

    public void setHeightFan(int heightFan) {
        this.heightFan = Math.abs(heightFan);
        this.fanChar = new char[this.heightFan * 2][this.heightFan * 2]; // TODO: maybe it have to be one other function
    }


    protected void PrintFan() {
        System.out.printf("\n");
        for (int axisY = 0; axisY < heightFan * 2; axisY++) {
            for (int axisX = 0; axisX < heightFan * 2; axisX++) {
                System.out.printf(String.valueOf(fanChar[axisX][axisY]));
            }
            System.out.printf("\n");
        }
    }
}

abstract class QuarterFanBuild extends Fan {

    abstract void Build(int X, int Y);

//    protected void BuildFanBlur() {
//        System.out.printf("\n");
//        for (int axisY = 0; axisY < heightFan * 2; axisY++) {
//            for (int axisX = 0; axisX < heightFan * 2; axisX++) {
//                this.fanChar[axisX][axisY] = 'X';  // BLUR
//            }
//            System.out.printf("\n");
//        }
//    }

    public void buildQuarterFan() {
        for (int axisX = 0; axisX < getHeightFan(); axisX++) {
            for (int axisY = 0; axisY < getHeightFan(); axisY++) {
                Build(axisX, axisY);
            }
        }
    }
}

class LeftQuarterFanBuild extends QuarterFanBuild {


    @Override
    public void Build(int axisX, int axisY) {
        if (axisX <= axisY) {
            this.fanChar[axisX][axisY] = '*';
        } else {
            this.fanChar[axisX][axisY] = '.';
        }
    }
}

class RightQuarterFanBuild extends QuarterFanBuild {

    @Override
    public void Build(int axisX, int axisY) {
        if (axisX >= axisY) {
            this.fanChar[axisX][axisY] = '*';
        } else {
            this.fanChar[axisX][axisY] = '.';
        }
    }
}

abstract class RotateBuildFan extends Fan {

    abstract void Rotate(Fan a);
}

class QuarterRotateBuildFan extends RotateBuildFan {

    void Rotate(Fan a) {
        int axisXhelper = 0;
        for (int axisX = a.heightFan - 1; axisX >= 0; axisX--) {
            for (int axisY = 0; axisY < a.heightFan; axisY++) {
                a.fanChar[axisXhelper][axisY + a.heightFan] = a.fanChar[axisX][axisY];
            }
            axisXhelper++;
        }
    }
}

class HalfRotateBuilderFan extends RotateBuildFan {

    void Rotate(Fan a) {
        int axiosXhelper = a.heightFan;
        for (int axisX = a.heightFan - 1; 0 <= axisX; axisX--) {
            int axisYhelper = 0;
            for (int axisY = a.heightFan * 2 - 1; 0 <= axisY; axisY--) {
                a.fanChar[axiosXhelper][axisYhelper] = a.fanChar[axisX][axisY];
                axisYhelper++;
            }
            axiosXhelper++;
        }
    }
}


public class FANGEN {
    public static void main(String[] args) {

        int heigh = 0;
        QuarterFanBuild q = null;
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("Enter number height fan");
            heigh = Integer.parseInt(s.nextLine());
        } catch (Exception ex) {
            System.out.println("Wrong enter");
            System.exit(0);
        }


        if (heigh == 0) {
            System.exit(0);
        } else if (heigh < 0) {
            q = new LeftQuarterFanBuild();
        } else if (heigh > 0) {
            q = new RightQuarterFanBuild();
        }
        q.setHeightFan(heigh);
       // q.BuildFanBlur();

        q.buildQuarterFan();

        RotateBuildFan a = new QuarterRotateBuildFan();
        a.Rotate(q);
        a = new HalfRotateBuilderFan();
        a.Rotate(q);

        q.PrintFan();


        //  System.out.println(q.getFan());
        System.exit(0);
    }
}
