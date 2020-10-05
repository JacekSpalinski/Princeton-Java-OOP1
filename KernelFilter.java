
import java.awt.Color;

public class KernelFilter {


    // Returns a new picture that applies an arbitrary kernel filter to the given picture.
    private static Picture kernel(Picture picture, double[][] weights) {
        Picture pic = new Picture(picture);
        int height = picture.height();
        int width = picture.width();
        double[][] fil = new double[weights.length][weights.length];
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights.length; j++) {
                fil[i][j] = weights[i][j];
            }
        }

        int[][] col = new int[width][height];

        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                Color color = picture.get(c, r);
                col[c][r] = color.getRed();
            }
        }

        int[][] newred = new int[width][height];
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                int sum = 0;
                double subSum = 0;
                for (int i = -(fil.length / 2); i < fil.length / 2 + 1; i++) {
                    for (int j = -(fil.length / 2); j < fil.length / 2 + 1; j++) {
                        int row = (r + i + height) % height;
                        int column = (c + j + width) % width;
                        subSum = subSum + col[column][row] * fil[i + fil.length / 2][j
                                + fil.length / 2];
                        sum = (int) Math.round(subSum);
                    }
                }
                if (sum < 0) newred[c][r] = 0;
                else if (sum > 255) newred[c][r] = 255;
                else newred[c][r] = sum;
            }
        }

        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                Color color = picture.get(c, r);
                col[c][r] = color.getGreen();
            }
        }

        int[][] newgreen = new int[width][height];
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                int sum = 0;
                double subSum = 0;
                for (int i = -(fil.length / 2); i < fil.length / 2 + 1; i++) {
                    for (int j = -(fil.length / 2); j < fil.length / 2 + 1; j++) {
                        int row = (r + i + height) % height;
                        int column = (c + j + width) % width;
                        subSum = subSum + col[column][row] * fil[i + fil.length / 2][j
                                + fil.length / 2];
                        sum = (int) Math.round(subSum);
                    }
                }
                if (sum < 0) newgreen[c][r] = 0;
                else if (sum > 255) newgreen[c][r] = 255;
                else newgreen[c][r] = sum;
            }
        }

        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                Color color = picture.get(c, r);
                col[c][r] = color.getBlue();
            }
        }

        int[][] newblue = new int[width][height];
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                int sum = 0;
                double subSum = 0;
                for (int i = -(fil.length / 2); i < fil.length / 2 + 1; i++) {
                    for (int j = -(fil.length / 2); j < fil.length / 2 + 1; j++) {
                        int row = (r + i + height) % height;
                        int column = (c + j + width) % width;
                        subSum = subSum + col[column][row] * fil[i + fil.length / 2][j
                                + fil.length / 2];
                        sum = (int) Math.round(subSum);
                    }
                }
                if (sum < 0) newblue[c][r] = 0;
                else if (sum > 255) newblue[c][r] = 255;
                else newblue[c][r] = sum;
            }
        }

        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                Color color = new Color(newred[c][r], newgreen[c][r], newblue[c][r]);
                pic.set(c, r, color);
            }
        }

        return pic;
    }


    // Returns a new picture that applies the identity filter to the given picture.
    public static Picture identity(Picture picture) {
        double[][] weights = {
                { 0, 0, 0 },
                { 0, 1, 0 },
                { 0, 0, 0 }
        };
        return kernel(picture, weights);
    }


    // Returns a new picture that applies a Gaussian blur filter to the given picture.
    public static Picture gaussian(Picture picture) {
        double[][] weights = {
                { 1.0 / 16, 1.0 / 8, 1.0 / 16 },
                { 1.0 / 8, 1.0 / 4, 1.0 / 8 },
                { 1.0 / 16, 1.0 / 8, 1.0 / 16 }
        };
        return kernel(picture, weights);
    }


    // Returns a new picture that applies a sharpen filter to the given picture.
    public static Picture sharpen(Picture picture) {
        double[][] weights = {
                { 0, -1, 0 },
                { -1, 5, -1 },
                { 0, -1, 0 }
        };
        return kernel(picture, weights);
    }


    // Returns a new picture that applies an Laplacian filter to the given picture.
    public static Picture laplacian(Picture picture) {
        double[][] weights = {
                { -1, -1, -1 },
                { -1, 8, -1 },
                { -1, -1, -1 }
        };
        return kernel(picture, weights);
    }


    // Returns a new picture that applies an emboss filter to the given picture.
    public static Picture emboss(Picture picture) {
        double[][] weights = {
                { -2, -1, 0 },
                { -1, 1, 1 },
                { 0, 1, 2 }
        };
        return kernel(picture, weights);
    }


    // Returns a new picture that applies a motion blur filter to the given picture.
    public static Picture motionBlur(Picture picture) {
        double[][] weights = {
                { 1.0 / 9, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1.0 / 9, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1.0 / 9, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1.0 / 9, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1.0 / 9, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1.0 / 9, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 1.0 / 9, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1.0 / 9, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 1.0 / 9 }
        };
        return kernel(picture, weights);
    }


    // Test client (ungraded)
    public static void main(String[] args) {
        Picture picture = new Picture(args[0]);
        Picture pic1 = identity(picture);
        Picture pic2 = gaussian(picture);
        Picture pic3 = sharpen(picture);
        Picture pic4 = laplacian(picture);
        Picture pic5 = emboss(picture);
        Picture pic6 = motionBlur(picture);
        pic1.show();
        pic2.show();
        pic3.show();
        pic4.show();
        pic5.show();
        pic6.show();

    }
}
