package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


   @Test
   public void 회원가입() throws Exception {
       //given
       Member member = new Member();
       member.setName("spring");

       //when
       Long saveId = memberService.join(member);

       //then
       Member findMember = memberService.findOne(saveId).get();
       Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
   }

   @Test
   public void 중복회원예외() throws Exception {
       //given
       Member member1 = new Member();
       member1.setName("spring1");

       Member member2 = new Member();
       member2.setName("spring1");

       //when
       memberService.join(member1);
       IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

       //then
   }
}