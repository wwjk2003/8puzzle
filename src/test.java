
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int[][] a = new int[5][6],b;
int c,d;
for (int i = 0; i < 5; i++) {
	for (int j = 0; j < 6; j++) {
		a[i][j] = i+j;
	}
}
 b = a;
b[4][5] = 0;
 c=10;
 d=c;
 d--;
 System.out.println(c);
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 6; j++) {
			System.out.print(a[i][j]+" ");
		}
		System.out.println("");
	}
	}

}
