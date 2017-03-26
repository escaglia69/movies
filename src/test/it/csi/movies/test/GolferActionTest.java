package it.csi.movies.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class GolferActionTest extends SeamTest {

	@Test
	public void test_golferAction() throws Exception {
		new FacesRequest() {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{golferAction.golferAction}");
			}
		}.run();
	}
}
