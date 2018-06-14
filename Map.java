public class Map{
	int x;
	int y;
	int numMine;
	int[][] map;
	public Map(int x,int y,int numMine){
		this.x=x;
		this.y=y;
		this.numMine=numMine;
		this.map=new int[x][y];
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				this.map[i][j]=0;
			}
		}
		Gen();
	}
	public void Gen(){
		//put mines
		for(int i=0;i<this.numMine;i++){
			point2 l=GenMine();
			if(this.map[l.x][l.y]==-1){
				i--;
			}
			else{
				this.map[l.x][l.y]=-1;
			}
		}
		//put numbers
		for(int i=0;i<this.x;i++){
			for(int j=0;j<this.y;j++){
				if(this.map[i][j]!=-1){
					this.map[i][j]=search(i, j);
				}
			}
		}
	}
	public boolean check(int x,int y){
		if(x>=0&&x<this.x&&y>0&&y<this.y){
			return true;
		}
		return false;
	}
	public int search(int x,int y){
		int num=0;
		for(int i=-1;i<2;i++){
			for(int j=-1;j<2;j++){
				if(check(x+i,y+j)&&this.map[x+i][y+j]==-1){
					num++;
				}
			}
		}
		return num;
	}
	public point2 GenMine(){
		int x=(int)(Math.random()*this.x);
		int y=(int)(Math.random()*this.y);
		point2 result=new point2(x,y);
		//System.out.printf("%d %d\n",result.x,result.y);
		return result;
	}
	public void print(){
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				System.out.printf("%d ",this.map[i][j]);
			}
			System.out.printf("\n");
		}
	}
	class point2{
		public int x;
		public int y;
		public point2(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
}
