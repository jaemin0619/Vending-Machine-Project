package Person;

// ������ Ŭ����
public class Admin {
	
	public static int totalMoney = 0;  // �� ����� �⺻�� ����(��������)
	public static String password;  // ��й�ȣ(��������)
	
	public Admin(){}  // ������
	
	public static int getTotalMoney() {
		return totalMoney;
	}

	public static void setTotalMoney(int totalMoney) {
		Admin.totalMoney = totalMoney;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public static void setPassword(String password) {
		// ��й�ȣ ����
		Admin.password = password;
	}
}
