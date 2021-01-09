import { TestBed } from '@angular/core/testing';

import { JdmServiceService } from './jdm-service.service';

describe('JdmServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: JdmServiceService = TestBed.get(JdmServiceService);
    expect(service).toBeTruthy();
  });
});
