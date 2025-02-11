package yapp.bestFriend.model.entity;

import lombok.Getter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


/**
 * BaseInfo는 모든 Entity의 상위 클래스가 되어 Entity들의 생성일시, 수정일시를 자동으로 관리하는 역할
 * JPA Entity들이 @MappedSupperclass가 선언된 클래스를 상속할 경우 클래스의 필드들도 칼럼으로 인식하도록 한다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "deleted=false")
public abstract class BaseInfo {

    // Entity가 생성되어 저장될 때 시간이 자동 저장됩니다.
    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime createdAt;

    @CreatedBy
    private Long createdBy;

    // 조회한 Entity 값을 변경할 때 시간이 자동 저장됩니다.
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private Long updatedBy;

    private boolean deletedYn = Boolean.FALSE;

    public void delete() {
        this.deletedYn = true;
    }

    public void restore() {
        this.deletedYn = false;
    }
}
