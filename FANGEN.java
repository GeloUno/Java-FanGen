import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Java 11

class Fan {
    private String fan = "";

    public String getFan() {
        return fan;
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
                fan = fan + fanChar[axisX][axisY];
            }
            fan = fan + "\n";
        }
    }
}

abstract class QuarterFanBuild extends Fan {

    abstract void Build();
}

class LeftQuarterFanBuild extends QuarterFanBuild {


    @Override
    public void Build() {
        for (int axisX = 0; axisX < getHeightFan(); axisX++) {
            for (int axisY = 0; axisY < getHeightFan(); axisY++) {
                if (axisX <= axisY) {
                    this.fanChar[axisX][axisY] = '*';
                } else {
                    this.fanChar[axisX][axisY] = '.';
                }
            }

        }
    }
}

class RightQuarterFanBuild extends QuarterFanBuild {

    @Override
    public void Build() {
        for (int axisX = 0; axisX < getHeightFan(); axisX++) {
            for (int axisY = 0; axisY < getHeightFan(); axisY++) {
                if (axisX >= axisY) {
                    this.fanChar[axisX][axisY] = '*';
                } else {
                    this.fanChar[axisX][axisY] = '.';
                }
            }

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
        int axisYhelper;
        for (int axisX = a.heightFan - 1; 0 <= axisX; axisX--) {
            axisYhelper = 0;
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

        List<Integer> fans = new ArrayList<>();
        String s1;
        int heigh = 0;
        QuarterFanBuild fanQuarte = null;

        do {
            Scanner s = new Scanner(System.in);
            s1 = (s.nextLine());
            try {
                heigh = Integer.parseInt(s1);
                if (Math.abs(heigh) <= 200) {
                    fans.add(heigh);
                }
            } catch (Exception ex) {
                heigh = 1;
            }
        } while (heigh != 0);


        for (Integer f : fans) {
            heigh = f;

            if (heigh == 0) {
                System.exit(0);
            } else if (heigh > 0) {
                fanQuarte = new LeftQuarterFanBuild();
            } else if (heigh < 0) {
                fanQuarte = new RightQuarterFanBuild();
            }
            fanQuarte.setHeightFan(heigh);
            // fanQuarte.BackgroundBlurBuildFan();

            fanQuarte.Build();

            RotateBuildFan rotateBuilderFan = new QuarterRotateBuildFan();
            rotateBuilderFan.Rotate(fanQuarte);
            rotateBuilderFan = new HalfRotateBuilderFan();
            rotateBuilderFan.Rotate(fanQuarte);

            fanQuarte.PrintFan();
            System.out.printf(fanQuarte.getFan());

        }
        System.exit(0);
    }
}
