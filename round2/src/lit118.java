import java.util.ArrayList;
import java.util.List;

class lit118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> all_rows = new ArrayList<>();
        List<Integer> row = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);

            for (int j = 1; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }

            all_rows.add(new ArrayList<>(row));
        }

        return all_rows;
    }

    public static void main(String[] args) {
        lit118 sol = new lit118();
        System.out.println(sol.generate(5));
    }
}
