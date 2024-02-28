import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserClass, Long> {
    
    @Query(value = "SELECT DISTINCT session_id FROM user_class WHERE queue_flag = 'N' ORDER BY id LIMIT 1", nativeQuery = true)
    String findNextSessionIdOrderByUserId();

    @Query(value = "SELECT * FROM user_class WHERE session_id = :sessionId AND queue_flag = 'N' ORDER BY id LIMIT 25", nativeQuery = true)
    List<UserClass> findNext25RowsForSessionId(@Param("sessionId") String sessionId);
}
