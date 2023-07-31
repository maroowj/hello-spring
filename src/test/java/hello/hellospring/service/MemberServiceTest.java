package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


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

   @Test
   public void 전체회원조회() throws Exception {
       //given

       //when

       //then
   }

   @Test
   public void 단건회원조회() throws Exception {
       //given

       //when

       //then
   }
}