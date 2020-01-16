package mate.academy.internetshop.generatorid;

public class RoleIdGenerator {
    private static Long idGenerator = 0L;

    public RoleIdGenerator() {
    }

    public static Long getIdGenerator() {
        return idGenerator++;
    }
}
