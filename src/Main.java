import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static int nBooks;
	public static int[] booksPrice;
	public static int money;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String line= br.readLine();
		
		while(line!=null) {
			nBooks=Integer.parseInt(line);
			booksPrice=new int[nBooks];

			line=br.readLine();
			String[] parts= line.split(" ");

			for(int i=0; i<nBooks;i++) {
				booksPrice[i]= Integer.parseInt(parts[i]);
			}
			Arrays.sort(booksPrice);

			line=br.readLine();
			money=Integer.parseInt(line);

			int price1=0;
			int price2=0;
			int count=0;
			for(int i=0; i<nBooks;i++) {

				int numb=money-booksPrice[i];
				int pos= binarySearch(numb, i);
				int temp1=0;
				int temp2=0;
				if(pos>=0) {
					if(booksPrice[i]<booksPrice[pos]) {
						temp1=booksPrice[i];
						temp2=booksPrice[pos];
					}else {
						temp1=booksPrice[pos];
						temp2=booksPrice[i];
					}

					count++;

					if(count==1) {
						price1=temp1;
						price2=temp2;			
					}else if(count>1) {
						if((temp2-temp1)<(price2-price1)) {
							price1=temp1;
							price2=temp2;	
						}
					}
				}

			}
			bw.write("Peter should buy books whose prices are "+price1+" and "+price2+".\n\n");
			line=br.readLine();
		}
		


		br.close();
		bw.close();

	}
	
	public static int binarySearch(int numb, int k){
		int pos = -1;
		int i=0;
		int j=booksPrice.length-1;
		
		while(i<=j && pos<0){
	
			int m= (i+j)/2;
			
			if(m!=k && booksPrice[m]==numb){
				pos =m;
			}else if(booksPrice[m]>numb){
				j=m-1;
			}else{
				i=m+1;
			}
		}
		return pos;
	}

}
