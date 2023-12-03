package solutions;

public abstract class SolutionExecutor {

    protected SolutionExecutor(Integer number) {
        System.out.println("""
                      _\\/_
                       /\\
                       /\\
                      /  \\
                      /~~\\o
                     /o   \\
                    /~~*~~~\\
                   o/    o \\
                   /~~~~~~~~\\~`
                  /__*_______\\
                       ||
                     \\====/
                      \\__/
                Solving for day %d
                  """.formatted(number));
    }

    public abstract void solve();
}
